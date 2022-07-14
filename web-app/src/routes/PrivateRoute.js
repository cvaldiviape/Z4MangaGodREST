import { Navigate } from 'react-router-dom';

const PrivateRoute = (props) => {
  const {isAuth, children} = props;

  return (
    <>
      { isAuth ? children : <Navigate to="/auth/login"/> }
    </>
  )
}

export default PrivateRoute