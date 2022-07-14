import React from 'react';

const IconControlDashboard = (props) => {
  const { open, setOpen } = props;
  return (
    <>
      <img
        src="../assets/control.png"
        alt=""
        onClick={() => setOpen(!open)}
        className={`${!open && 'rotate-180'} absolute -right-3 top-9 w-7 border-2 border-primary cursor-pointer rounded-full`}
      />
    </>
  )
}

export default IconControlDashboard