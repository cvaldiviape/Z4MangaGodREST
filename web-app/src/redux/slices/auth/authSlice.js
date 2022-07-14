import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    responseData: {
      user: null,
      token: null,
      role: null,
    },
    responseSuccess: false,
    responseMessage: "",
  },
  reducers: {
    requestAuth() {

    },
    setResponseDataAuth(state, action) {
      state.responseData = action.payload;
    },
    setResponseSuccessAuth(state, action) {
      state.responseSuccess = action.payload;
    },
    setResponseMessageAuth(state, action) {
      state.responseMessage = action.payload;
    },
    requestLogout() {

    },
    setLogout(state) {
      state.responseData = {
        user: null,
        token: null,
        role: null,
      };
      state.responseSuccess = false;
      state.responseMessage = "";
    }
  }
});

// Action creators are generated for each case reducer function
export const {
  requestAuth,
  setResponseDataAuth,
  setResponseSuccessAuth,
  setResponseMessageAuth,
  requestLogout,
  setLogout
} = authSlice.actions;