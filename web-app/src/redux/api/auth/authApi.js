import { instanceAxiosWithoutAuthorization } from '../baseApi';

export const apiRequestAuth = async (authDto) => {
  return await instanceAxiosWithoutAuthorization.post('auth/login', JSON.stringify(authDto));
};

export const apiRequestRefreshToken = async (tokenDto) => {
  return await instanceAxiosWithoutAuthorization.post('auth/refresh-token', JSON.stringify(tokenDto));
};