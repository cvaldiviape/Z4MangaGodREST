import './InputCustom.css';

const InputCustom = (props) => {
  const { type, name, label, onChange, value, ref, width = "" } = props;

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
          type={type}
          name={name}
          value={value}
          onChange={handleChangeExits}
          className={`${width} h-12 px-2 text-base bg-white border-[1px] border-gray-500 border-opacity-50 rounded-md outline-none
                    focus:border-blue-700 text-gray-800 focus:border-2 transition duration-200`}
        />
        <span
          className={`text-base text-gray-500 text-opacity-80 absolute left-1 top-3 mx-1 px-1 transition duration-200 
                      input-text ${value && 'text-gray-600 bg-white transform -translate-y-5 -translate-x-1 text-xs'}`}
        >
          {label}
        </span>
      </label>
    </div>
  )
}

export default InputCustom