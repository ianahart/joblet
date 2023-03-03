import { useMemo, useEffect, useState } from 'react';
import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { http, retreiveTokens } from './utils';
import { UserContext } from '../context/user';
import { ITokens, IUserContext } from '../interfaces';

interface IProps {
  children: JSX.Element;
}

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
      (res) => {
        return res;
      },
      async (error) => {
        console.log(error);
        const originalRequest = error.config;
        const notAuthenticated =
          error.response?.data?.code === 'bad_authorization_header' ||
          error.response?.data?.detail?.toLowerCase() ===
            'authentication credentials were not provided.';
        if (error.response?.status === 403 && notAuthenticated) {
          setLoaded(true);
        } else if (
          error.response.status === 403 &&
          originalRequest.url.includes('/auth/refresh')
        ) {
          console.log('hi');
          return Promise.reject(error);
        } else if (error.response.status === 403 && !originalRequest._retry) {
          console.log('correct');
          originalRequest._retry = true;
          const storage = retreiveTokens();
          if (storage?.refreshToken) {
            const response = await http.post(`/auth/refresh`, {
              refreshToken: storage.refreshToken,
            });

            const token: string = response.data.token;
            const tokens = retreiveTokens();
            tokens.token = token;
            localStorage.setItem('tokens', JSON.stringify(tokens));

            //@ts-ignore
            setTokens((prevState: ITokens) => ({
              ...prevState,
              token,
            }));
            return http(originalRequest);
          }
        }
        if (error.response.status === 403 || error.response.status === 401) {
          logout();
          if (!isLoaded) {
            setLoaded(true);
          }
          return;
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
