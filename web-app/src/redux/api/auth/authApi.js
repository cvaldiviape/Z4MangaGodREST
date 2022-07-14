import { instanceAxiosWithoutAuthorization, instanceAxios } from '../baseApi';

export const apiRequestAuth = async (authDto) => {
  const response = await instanceAxios.post('auth/login', JSON.stringify(authDto));
  return response;
};

export const apiRequestRefreshToken = async (tokenDto) => {
  const response = await instanceAxiosWithoutAuthorization.post('auth/refresh-token', JSON.stringify(tokenDto));
  return response;
};