import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    responseDataListUsers: [],
    responseDataUser: null,
    responseDataPagination: null,
    loading: false,
  },
  reducers: {
    requestGetListUsers() {

    },
    setResponseGetListUsers(state, action) {
      state.responseDataListUsers = action.payload;
    },
    requestGetUserByID() {

    },
    setResponseGetUserByID(state, action) {
      state.responseDataUser = action.payload;
    },
    requestCreateUser() {

    },
    setResponseCreateUser(state, action) {
      state.responseDataUser = action.payload;
    },
    requestUpdateUser() {

    },
    setResponseUpdateUser(state, action) {
      state.responseDataUser = action.payload;
    },
    requestDeleteUser() {

    },
    setResponseDeleteUser(state, action) {
      state.responseDataUser = action.payload;
    },
  }
});

// Action creators are generated for each case reducer function
export const { 
  requestGetListUsers,
  setResponseGetListUsers,
  requestGetUserByID,
  setResponseGetUserByID,
  requestCreateUser,
  setResponseCreateUser,
  requestUpdateUser,
  setResponseUpdateUser,
  requestDeleteUser,
  setResponseDeleteUser,
 } = userSlice.actions;