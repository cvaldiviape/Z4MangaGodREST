import { call, takeLatest, put } from "redux-saga/effects";
import { RESPONSE_AUTH_BAD_CREDENTIALS } from "../../../util/constants/authConstans";
import { apiRequestAuth } from '../../api/auth/authApi';
import { requestAuth, setResponseDataAuth, setResponseSuccessAuth, setResponseMessageAuth, requestLogout, setLogout } from "../../slices/auth/authSlice";

// generators
function* handleRequestAuth(action) {
  try {
    const { authDto } = action.payload;
    const response = yield call(apiRequestAuth, authDto);

    yield put(setResponseDataAuth(response.data.data));
    yield put(setResponseSuccessAuth(response.data.success));
    yield put(setResponseMessageAuth(response.data.message));

  } catch (e) {
    console.log(e);
    const messageResponse = e?.response?.data?.message;
    if (messageResponse === RESPONSE_AUTH_BAD_CREDENTIALS) {
      yield put(setResponseMessageAuth("Credenciales incorrectas."));
    } else {
      yield put(setResponseMessageAuth("Error del servidor."));
    }
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestAuth() {
  //()
  yield takeLatest(requestAuth, handleRequestAuth);
}

// generators
function* handleRequestLogout(action) {
  try {
    yield put(setLogout());
  } catch (e) {
    console.log(e);
  }
}

// watchers - se encargan de yeldear efectos
export function* watchRequestLogout() {
  //()
  yield takeLatest(requestLogout, handleRequestLogout);
}

