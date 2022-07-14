import './ButtonCustomF.css';

const ButtonCustomF = () => {
  return (
    <>
      <button className="inline-flex relative w-full h-10 text-black text-sm justify-center items-center 
                         font-bold border-none cursor-pointer rounded-md uppercase transition dutarion-300 overflow-hidden
                         seis ">
        <span className="">Boton</span>
        <svg className="h-full w-full absolute top-0 left-0">
          <rect x="0" y="0" fill="none"></rect>
        </svg>
      </button>
    </>
  )
}

export default ButtonCustomF