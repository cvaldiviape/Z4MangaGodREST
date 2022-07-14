import React from 'react';
import logo from '../../../assets/logoSmall.jpg';

const LogoDashboard = (props) => {
  const {open} = props;

  return (
    <>
      <div className="flex gap-x-4 items-center">
          <img 
            src={logo} 
            alt="" 
            className={`cursor-pointer ${open && 'rotate-[360deg]'} duration-500`}
          />
          <h1 
            className={`text-white font-medium ${!open && 'scale-0'} origin-left duration-300`} 
            style={{fontFamily: "Fighting"}}
          >
           MANGAGOD
          </h1>
        </div>
    </>
  )
}

export default LogoDashboard