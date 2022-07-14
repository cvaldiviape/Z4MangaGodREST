import React from 'react';
import { useDispatch } from 'react-redux';
import { logout } from '../../../redux/slices/auth/authSlice';
import ButtonNormal from '../../components/buttonNormal/buttonNormal';

const NavbarDashboard = (props) => {
  const dispatch = useDispatch();

  const closeSession = () => {
    dispatch(logout());
  }

  return (
    <div className="bg-red-600">
      <h1>NavbarDashboard</h1>
      <ButtonNormal onClick={closeSession}>Cerrar sesion</ButtonNormal>
    </div>
  )
}

export default NavbarDashboard