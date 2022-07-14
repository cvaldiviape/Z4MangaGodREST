import { RESPONSE_AUTH_DATA, RESPONSE_AUTH_TOKEN, RESPONSE_AUTH_ROLE } from "../util/constants/authConstans";

export const setStorageAuthData = (authData) => {
  localStorage.setItem(RESPONSE_AUTH_DATA, JSON.stringify(authData));
}

export const setStorageTokenAccess = (token) => {
  localStorage.setItem(RESPONSE_AUTH_TOKEN, JSON.stringify(token));
}

export const setStorageRole = (role) => {
  localStorage.setItem(RESPONSE_AUTH_ROLE, JSON.stringify(role));
}

const getStorageTokenAccess = () => {
  let token = null;
  let tokenInfo = localStorage.getItem(RESPONSE_AUTH_TOKEN);
  let jsonTokenInfo = JSON.parse(tokenInfo);
  if (jsonTokenInfo?.tokenAccess) {
    token = jsonTokenInfo?.tokenAccess;
  }
  return token;
}

export const getStorageIsAuth = () => {
  let token = getStorageTokenAccess();
  //console.log(token);
  return token ? true : false;
}