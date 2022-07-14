import React from 'react';
import './ButtonNormal.css'

const noop = () => {};

const ButtonNormal = (props) => {
  const { disabled = false, onClick = noop, type = "button", width = "", textSize = "text-sm", fontWeight = "font-medium", 
          textColor = "text-white", justify = "justify-center", transformText = "", children } = props;

  return (
    <>
      <button
        disabled={disabled}
        type={type}
        onClick={onClick}
        className={`${width} ${textColor} bg-primary-800 focus:ring-4 focus:outline-none focus:ring-primary-300 
                    ${fontWeight} rounded-lg ${textSize} px-5 py-1 text-center dark:bg-primary-600 dark:focus:ring-primary-800 
                    inline-flex items-center ${justify} ${transformText} inline-flex relative transition dutarion-300 overflow-hidden 
                    btn`}
      >
        <span className="relative z-20">
          {children}
        </span>
    </button>
    </>
  )
}

export default ButtonNormal