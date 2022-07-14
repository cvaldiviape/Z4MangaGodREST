import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react'
import './ButtonTextIcon.css';

const ButtonTextIcon = (props) => {
  const {
    type, 
    disabled=false, 
    onClick, 
    text, 
    icon, 
    bgColor='bg-red-600', 
    bgColorHover='', 
    txtColor='text-white', 
    txtColorHover='text-white', 
    uppercase=false, 
    fullWidth, 
    className=''
  } = props;
  const [hover, setHover] = useState('');

  useEffect(() => {
    if(disabled===false){
      let h = `hover:${bgColorHover} hover:${txtColorHover}`
      setHover(h);
    }
  }, [disabled, hover, setHover, bgColorHover, txtColorHover]);
 
  return (
    <>
      <button 
        type={type}
        disabled={disabled}
        onClick={onClick}
        className={`flex justify-center items-center h-10 border-none border-transparent rounded-md text-sm font-bold
                  ${bgColor} ${txtColor} ${uppercase && 'uppercase'}
                  ${fullWidth && 'w-full'} ${disabled===false && ('cursor-pointer ' + hover)} ${className}`}>
        { icon && <FontAwesomeIcon className="pr-2 scale-125" icon={icon} />}
        { text ? text : 'Button' }
      </button>
    </>
  )
}

export default ButtonTextIcon