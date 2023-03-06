export const createAccountState = {
  firstName: { name: 'firstName', value: '', error: '', type: 'text' },
  lastName: { name: 'lastName', value: '', error: '', type: 'text' },
  email: { name: 'email', value: '', error: '', type: 'email' },
  password: { name: 'password', value: '', error: '', type: 'password' },
};

export const loginState = {
  email: { name: 'email', value: '', error: '', type: 'email' },
  password: { name: 'password', value: '', error: '', type: 'password' },
};

export const passwordResetState = {
  oldPassword: { name: 'oldPassword', value: '', error: '', type: 'password' },
  newPassword: { name: 'newPassword', value: '', error: '', type: 'password' },
  confirmPassword: { name: 'confirmPassword', value: '', error: '', type: 'password' },
};

export const tokenState = {
  refreshToken: '',
  token: '',
};

export const userState = {
  id: 0,
  firstName: '',
  lastName: '',
  email: '',
  role: '',
};
