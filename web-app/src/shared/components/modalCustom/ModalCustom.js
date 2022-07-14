import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faCircleExclamation } from '@fortawesome/free-solid-svg-icons';
import ButtonNormal from '../buttonNormal/buttonNormal';

const noop = () => { };

const ModalCustom = (props) => {
  const { open = false, setOpen = noop, title = "Titulo", message = "Mensaje", position ="items-center" } = props;

  return (
    <>
      {
        open
        &&
        <div className={`w-screen h-screen fixed top-0 left-0 bg-[rgba(0,0,0,0.5)] z-20 flex ${position} justify-center p-10`}>
          <div className="w-11/12 sm:w-3/4 md:w-1/2 lg:w-1/3 min-w-28 bg-white relative rounded-md p-5 shadow-[rgba(100,100,111,0.2)] shadow-[0px 7px 29px 0px]">
            <div className="flex flex-col items-center justify-center">
              <div className="">
                <FontAwesomeIcon className="text-6xl text-red-600 bg-white scale-125 my-5" icon={faCircleExclamation} />
              </div>
              <h1 className="text-4xl font-bold mb-5">{title}</h1>
              <h2 className="text-base font-medium mb-5">{message}</h2>
              <ButtonNormal 
                onClick={() => setOpen(false)}
                textSize="text-md"
                transformText="uppercase"
              >
                Aceptar
              </ButtonNormal>
            </div>
          </div>
        </div>
      }
    </>
  )
}

export default ModalCustom