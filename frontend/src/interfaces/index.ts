export interface ILoginResponse {
  refreshToken: string;
  token: string;
  userDto: IUser;
}
export interface ICreateAccountForm {
  firstName: { name: string; value: string; error: string; type: string };
  lastName: { name: string; value: string; error: string; type: string };
  email: { name: string; value: string; error: string; type: string };
  password: { name: string; value: string; error: string; type: string };
}

export interface ILoginForm {
  email: { name: string; value: string; error: string; type: string };
  password: { name: string; value: string; error: string; type: string };
}

export interface IPasswordResetForm {
  oldPassword: { name: string; value: string; error: string; type: string };
  newPassword: { name: string; value: string; error: string; type: string };
  confirmPassword: { name: string; value: string; error: string; type: string };
}

export interface IEditProfileForm {
  email: { name: string; value: string; error: string; type: string };
  phoneNumber: { name: string; value: string; error: string; type: string };
  city: { name: string; value: string; error: string; type: string };
  state: { name: string; value: string; error: string; type: string };
  country: { name: string; value: string; error: string; type: string };
  resume: { name: string; value: string; error: string; type: string };
}

export interface IDropdownData {
  name: string;
  id: number;
}

export interface IProfile {
  city: string;
  country: string;
  email: string;
  phoneNumber: string;
  resume: string;
  state: string;
  id: number;
}

export interface IUser {
  id: number;
  profileId: number;
  firstName: string;
  lastName: string;
  email: string;
  role: string;
  abbreviation: string;
}

export interface ITokens {
  refreshToken: string;
  token: string;
}

export interface IUserContext {
  user: IUser;
  tokens: ITokens;
  logout: () => void;
  setUser: (user: IUser) => void;
  setTokens: (tokens: ITokens) => void;
  stowTokens: (tokens: ITokens) => void;
  updateUser: (user: IUser) => void;
}
