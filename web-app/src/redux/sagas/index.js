import { all, fork } from 'redux-saga/effects';
import { watchRequestAuth, watchRequestLogout } from "./auth/authSaga";
import { watchRequestGetListUsers, watchRequestGetUserByID, watchRequestCreateUser, watchRequestUpdateUser, watchRequestDeleteUser } from "./user/userSaga";

export default function* rootSaga () {

  yield all([
    fork(watchRequestAuth),
    fork(watchRequestLogout),
    fork(watchRequestGetListUsers),
    fork(watchRequestGetUserByID),
    fork(watchRequestCreateUser),
    fork(watchRequestUpdateUser),
    fork(watchRequestDeleteUser),
  ]);
}