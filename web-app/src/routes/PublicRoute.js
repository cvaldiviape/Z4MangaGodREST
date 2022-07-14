import React from 'react'
import { Navigate } from 'react-router-dom';

const PublicRoute = (props) => {
  const {isAuth, children} = props;

  return (
    <>{isAuth ? <Navigate to="/app/home"/> : children}</>
  )
}

export default PublicRoute