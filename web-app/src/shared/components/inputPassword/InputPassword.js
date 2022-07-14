import { useState } from 'react';
import './InputPassword.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';

const InputPassword = (props) => {
  const { name, label, onChange, value, ref, width="" } = props;
  const [open, setOpen] = useState(false);

  const toogleBtn = () => {
    setOpen(prevState => !prevState);
  }

  const handleChangeExits = (e) => {
    if (onChange) {
      onChange(e);
    }
  }

  return (
    <div className="flex justify-center items-center">
      <label className={`relative ${width}`}>
        <input 
          ref={ref} 
          type={open ? "text" : "password"} 
          name={name} value={value} 
          onChange={handleChangeExits} 
          className={`${width} h-12 px-2 text-base bg-white border-[1px] border-gray-500 border-opacity-50 rounded-md outline-none
                   focus:border-blue-700 text-gray-800 focus:border-2 transition duration-200`} />
        <span 
          className={`text-base text-gray-500 text-opacity-80 absolute left-1 top-3 mx-1 px-1 transition duration-200 
                      input-text ${value && 'text-gray-600 bg-white transform -translate-y-5 -translate-x-1 text-xs'}`}
        >
          {label}
        </span>
      </label>
      <button 
        type="button" 
        className="p-1.5 pl-3 bg-white text-base outline-none border-none -ml-12 cursor-pointer z-10" 
        onClick={toogleBtn}
      >
        <FontAwesomeIcon className="pr-2 scale-125" icon={open ? faEye : faEyeSlash} />
      </button>
    </div>
  )
}

export default InputPassword;