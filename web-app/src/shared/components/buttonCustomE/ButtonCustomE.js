import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons';
import './ButtonCustomE.css';

const ButtonCustomE = () => {
  return (
    <>
      <button className="inline-flex relative w-full h-10 bg-gray-900 text-white text-sm justify-center items-center 
                         font-bold border-none cursor-pointer rounded-md uppercase transition dutarion-300 overflow-hidden hover:bg-emerald-500 cinco">
        <div className="flex items-center absolute bg-yellow-400 -left-10 opacity-0 transition-all duration-300 icono">
          <FontAwesomeIcon className="bg-emerald-500 text-white h-4 w-4" icon={faArrowRight} />
        </div>
        <span className="absolute left-1/3 transition-all duration-400">Boton E</span>
      </button>
    </>
  )
}

export default ButtonCustomE