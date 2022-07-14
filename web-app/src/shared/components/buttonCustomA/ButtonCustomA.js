import './ButtonCustomA.css';

const noop = () => {};

const ButtonCustomA = (props) => {
  const {type, text, onClick = noop, disabled, bgColor, txtColor, txtSize} = props;

  return (
    <>
      <button 
        disabled={disabled}
        type={type} 
        onClick={onClick} 
        className={`inline-flex relative w-full h-10 ${!bgColor && 'bg-blue-600'} ${!txtColor && 'text-white'} ${!txtSize && 'text-sm'} 
                    justify-center items-center font-bold border-none ${disabled ? 'opacity-80':'cursor-pointer'} rounded-md uppercase transition 
                    dutarion-300 overflow-hidden uno`}>
        <span className="relative z-20">
          { text ? text : 'Button' }
        </span>
      </button>
    </>
  )
}

export default ButtonCustomA