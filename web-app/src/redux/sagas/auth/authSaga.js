import { call, takeLatest, put } from "redux-saga/effects";
import { MESSAGE_BAD_CREDENTIALS, MESSAGE_ERROR_SERVER_SPANISH } from "../../../util/constants/messageErrorConstans";
import { apiRequestAuth } from '../../api/auth/authApi';
import { requestAuth, logout, changeMessageAuth, 
         setResponseDataAuth, setResponseSuccessAuth, setResponseMessageAuth, setLogout } from "../../slices/auth/authSlice";

// generators
function* handleRequestAuth(action) {
  yield put(setResponseMessageAuth(""));
  try {
    const { authDto } = action.payload;
    const response = yield call(apiRequestAuth, authDto);

    yield put(setResponseDataAuth(response.data.data));
    yield put(setResponseSuccessAuth(response.data.success));
    yield put(setResponseMessageAuth(response.data.message));

  } catch (e) {
    console.log(e);
    const messageResponse = e?.response?.data?.message;
    if (messageResponse === MESSAGE_BAD_CREDENTIALS) {
      yield put(setResponseMessageAuth(MESSAGE_BAD_CREDENTIALS));
    } else {
      yield put(setResponseMessageAuth(MESSAGE_ERROR_SERVER_SPANISH));
    }
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestAuth() {
  //()
  yield takeLatest(requestAuth, handleRequestAuth);
}

// generators
function* handleRLogout(action) {
  try {
    yield put(setLogout());
  } catch (e) {
    console.log(e);
  }
}

// watchers - se encargan de yeldear efectos
export function* watchLogout() {
  //()
  yield takeLatest(logout, handleRLogout);
}

// generators
function* handleChangeMessageAuth(action) {
  try {
    yield put(setResponseMessageAuth(action.payload));
  } catch (e) {
    console.log(e);
  }
}

// watchers - se encargan de yeldear efectos
export function* watchChangeMessageAuth() {
  //()
  yield takeLatest(changeMessageAuth, handleChangeMessageAuth);
}

