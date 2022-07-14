import React from 'react'

const MenuDashboard = (props) => {
  const {menus, open} = props;

  return (
    <>
       <ul className="pt-6">
          {
            menus.map((item, index) => (
              <li key={index} className={`${item.gap ? 'mt-9':'mt-2'} ${(index === 0)  && 'bg-light-white'} text-gray-300 text-sm flex items-center gap-x-4 cursor-pointer p-2 hover:bg-light-white rounded-md`}>
                <img src={`../assets/${item.src}.png`} alt="" />
                <span className={`${!open && 'hidden'} origin-left duration-200`}>{item.title}</span>
              </li>
            ))
          }
        </ul>
    </>
  )
}

export default MenuDashboard