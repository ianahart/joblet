import { createContext, useState } from 'react';
import { IUserContext, IUser, ITokens } from '../interfaces';
import { userState, tokenState } from '../data/';
interface IChildren {
  children: React.ReactNode;
}

export const UserContext = createContext<IUserContext | null>(null);

const UserContextProvider = ({ children }: IChildren) => {
  const [user, setUser] = useState<IUser>(userState);
  const [tokens, setTokens] = useState<ITokens>(tokenState);

  const logout = () => {
    localStorage.clear();
    setTokens({ refreshToken: '', token: '' });
    setUser(userState);
  };

  const stowTokens = (tokens: ITokens) => {
    localStorage.setItem('tokens', JSON.stringify(tokens));
    setTokens((prevState) => ({
      ...prevState,
      ...tokens,
    }));
  };

  const updateUser = (user: IUser) => {
    setUser((prevState) => ({
      ...prevState,
      ...user,
    }));
  };

  return (
    <UserContext.Provider
      value={{ updateUser, logout, user, tokens, setUser, setTokens, stowTokens }}
    >
      {children}
    </UserContext.Provider>
  );
};

export default UserContextProvider;
