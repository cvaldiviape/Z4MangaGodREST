import { RESPONSE_AUTH_DATA } from '../../util/constants/authConstans';
import { END_POINT } from '../../util/constants/backendConstans';

const axios = require('axios');
let authorization = '';

if (localStorage.getItem(RESPONSE_AUTH_DATA)) {
  const auth = JSON.parse(localStorage.getItem(RESPONSE_AUTH_DATA));
  if (auth?.tokenAccess) {
    authorization = `${auth?.tokenType} ${auth?.tokenAccessn}`;
  }
}

export const instanceAxios = axios.create({
  baseURL: END_POINT,
  headers: {
    'Content-Type': 'application/json',
    'Authorization': authorization,
  }
});

export const instanceAxiosWithoutAuthorization = axios.create({
  baseURL: END_POINT,
  headers: {
    'Content-Type': 'application/json',
  }
});