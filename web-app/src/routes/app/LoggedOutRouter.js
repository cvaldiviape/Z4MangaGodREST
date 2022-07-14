import React from 'react'
import { Route, Routes, Navigate } from 'react-router-dom';
import Login from '../../pages/public/Login';
import Register from '../../pages/public/Register';
import ResetPassword from '../../pages/public/ResetPassword';

const LoggedOutRouter = () => {

  return (
    <>
      <Routes>
        <Route path="/" element={<Navigate to="/auth/login" replace />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/reset-password" element={<ResetPassword />} />
      </Routes>
    </>
  )
}

export default LoggedOutRouter