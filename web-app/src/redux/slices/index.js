import { combineReducers } from '@reduxjs/toolkit';
import { authSlice } from './auth/authSlice';
import { userSlice } from './user/userSlice';

// Redux: Root Reducer
const reducer = combineReducers({
  auth: authSlice.reducer,
  user: userSlice.reducer,
});

// Exports
export default reducer;