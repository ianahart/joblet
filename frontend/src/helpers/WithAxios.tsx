import { useMemo, useEffect, useState } from 'react';
import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { http, retreiveTokens } from './utils';
import { UserContext } from '../context/user';
import { ITokens, IUserContext } from '../interfaces';

interface IProps {
  children: JSX.Element;
}

let isRefreshing = false;
let failedQueue: any[] = [];

const processQueue = (error: any, token = null) => {
  failedQueue.forEach((prom) => {
    if (error) {
      prom.reject(error);
    } else {
      prom.resolve(token);
    }
  });

  failedQueue = [];
};

const WithAxios: React.FC<IProps> = ({ children }): JSX.Element => {
  const navigate = useNavigate();
  const [isLoaded, setLoaded] = useState(false);
  const { setTokens, logout } = useContext(UserContext) as IUserContext;
  useMemo(() => {
    const reqInterceptorId = http.interceptors.request.use(
      (config) => {
        if (retreiveTokens()?.token && config.url !== '/auth/refresh') {
          // @ts-ignore
          config.headers.Authorization = `Bearer ${retreiveTokens()?.token}`;
        }
        return config;
      },
      (error) => Promise.reject(error)
    );
    const resInterceptorId = http.interceptors.response.use(
      function (response) {
        return response;
      },
      function (error) {
        const originalRequest = error.config;

        if (error.response.status === 403 && !originalRequest._retry) {
          if (isRefreshing) {
            return new Promise(function (resolve, reject) {
              failedQueue.push({ resolve, reject });
            })
              .then((token: any) => {
                originalRequest.headers['Authorization'] = 'Bearer ' + token;
                return http(originalRequest);
              })
              .catch((err) => {
                return Promise.reject(err);
              });
          }

          originalRequest._retry = true;
          isRefreshing = true;

          const storage = retreiveTokens();
          return new Promise(function (resolve, reject) {
            http
              .post('/auth/refresh', { refreshToken: storage.refreshToken })
              .then((response) => {
                http.defaults.headers.common['Authorization'] =
                  'Bearer ' + response.data.token;
                originalRequest.headers['Authorization'] =
                  'Bearer ' + response.data.token;

                const token: string = response.data.token;
                const tokens = retreiveTokens();
                tokens.token = token;
                localStorage.setItem('tokens', JSON.stringify(tokens));

                //@ts-ignore
                setTokens((prevState: ITokens) => ({
                  ...prevState,
                  token,
                }));

                processQueue(null, response.data.token);
                resolve(http(originalRequest));
              })
              .catch((err) => {
                processQueue(err, null);
                reject(err);
              })
              .finally(() => {
                isRefreshing = false;
              });
          });
        }

        return Promise.reject(error);
      }
    );
    return () => {
      http.interceptors.response.eject(resInterceptorId);
      http.interceptors.request.eject(reqInterceptorId);
    };
  }, [setTokens, isLoaded, setLoaded]);
  useEffect(() => {
    if (isLoaded) {
      console.log('Are you running? You shouldnt be... WithAxios.tsx');
      navigate('/login');
      setLoaded(false);
    }
  }, [navigate, isLoaded]);

  return children;
};

export default WithAxios;
