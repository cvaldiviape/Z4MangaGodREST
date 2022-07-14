import { END_POINT } from '../../../util/constants/backendConstans';
import { instanceAxios } from '../baseApi';

export const apiRequestGetListUsers = async () => {
  return instanceAxios.get(`${END_POINT}/user`);
};

export const apiRequestGetUserByID = async (userId) => {
  return instanceAxios.get(`${END_POINT}/user/${userId}`);
};

export const apiRequestCreateUser = async (userDto) => {
  return instanceAxios.post(`${END_POINT}/user`, JSON.stringify(userDto));
};

export const apiRequestUpdateUser = async (userId, userDto) => {
  return instanceAxios.put(`${END_POINT}/user/${userId}`, JSON.stringify(userDto));
};

export const apiRequestDeleteUser = async (userId) => {
  return instanceAxios.delete(`${END_POINT}/user/${userId}`);
};