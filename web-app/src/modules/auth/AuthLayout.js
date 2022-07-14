import React from 'react';
import backgroundLogin from '../../assets/fondo.jpg';

const AuthLayout = (props) => {
  const { children } = props;

  return (
    <div className="flex flex-col md:flex-row h-screen items-center">
        <div className="bg-blue-600 h-screen hidden md:block w-full md:w-1/2 lg:w-2/3">
          <img className="w-full h-full object-cover" src={backgroundLogin} alt="background-login" />
        </div>
        <div className="bg-white h-screen w-full md:w-1/2 lg:w-1/3 flex items-center justify-center px-16 md:px-8">
          <div className="w-full h-100">
            <h1 className="text-6xl text-center" style={{ fontFamily: "Fighting" }}>Manga God</h1>
            <h1 className="text-xl md:text-2xl font-bold leading-tight mt-12 mb-1" style={{ fontFamily: "Roboto" }}>Bienvenido</h1>
            {children}
          </div>
        </div>
      </div>
  )
}

export default AuthLayout