import { call, takeLatest, put } from "redux-saga/effects";
import {
  apiRequestGetListUsers,
  apiRequestGetUserByID,
  apiRequestCreateUser,
  apiRequestUpdateUser,
  apiRequestDeleteUser,
} from '../../api/user/userApi';
import {
  
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
} from "../../slices/user/userSlice";

// generators
function* handleRequestGetListUsers() {
  try {
    const response = yield call(apiRequestGetListUsers);
    yield put(setResponseGetListUsers(response));
  } catch (e) {
    console.log("error handleRequestGetListUsers")
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestGetListUsers() {
  yield takeLatest(requestGetListUsers, handleRequestGetListUsers);
}

// generators
function* handleRequestGetUserByID() {
  try {
    const response = yield call(apiRequestGetUserByID);
    yield put(setResponseGetUserByID(response));
  } catch (e) {
    console.log("error handleRequestGetUserByID")
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestGetUserByID() {
  yield takeLatest(requestGetUserByID, handleRequestGetUserByID);
}

// generators
function* handleRequestCreateUser(action) {
  try {
    const { userDto } = action.payload;
    const response = yield call(apiRequestCreateUser, userDto);
    yield put(setResponseCreateUser(response));
  } catch (e) {
    console.log('error handleRequestCreateUser')
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestCreateUser() {
  yield takeLatest(requestCreateUser, handleRequestCreateUser);
}

// generators
function* handleRequestUpdateUser(action) {
  try {
    const { userId, userDto } = action.payload;
    const response = yield call(apiRequestUpdateUser, userId, userDto);
    yield put(setResponseUpdateUser(response));
  } catch (e) {
    console.log('error handleRequestUpdateUser')
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestUpdateUser() {
  yield takeLatest(requestUpdateUser, handleRequestUpdateUser);
}

// generators
function* handleRequestDeleteUser(action) {
  try {
    const { userId } = action.payload;
    const response = yield call(apiRequestDeleteUser, userId);
    yield put(setResponseDeleteUser(response));
  } catch (e) {
    console.log('error handleRequestDeleteUser')
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestDeleteUser() {
  yield takeLatest(requestDeleteUser, handleRequestDeleteUser);
}
