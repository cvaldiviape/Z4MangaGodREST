import React, { useEffect, useState } from 'react'
import IconControlDashboard from './IconControlDashboard';
import LogoDashboard from './LogoDashboard';
import MenuDashboard from './MenuDashboard';
import NavbarDashboard from './NavbarDashboard';

const SidebarDashboard = (props) => {
  const { children } = props;
  const [open, setOpen] = useState(true);
  const menus = [
    { title: "Dashboard", src: "Chart_fill" },
    { title: "Inbox", src: "Chat" },
    { title: "Accounts", src: "User", gap: true },
    { title: "Schedule ", src: "Calendar" },
    { title: "Search", src: "Search" },
    { title: "Analytics", src: "Chart" },
    { title: "Files ", src: "Folder", gap: true },
    { title: "Setting", src: "Setting" },
  ];

  useEffect(() => {
    window.addEventListener("resize", handleResize)
  }, [])

  const handleResize = () => {
    if (window.innerWidth < 720) {
      setOpen(false)
    } else {
      setOpen(true)
    }
  }

  return (
    <div className="flex">
      <div className={`${open ? 'w-72':'w-20'} h-screen p-5 pt-8 bg-primary-800 relative duration-300`}>
        <IconControlDashboard open={open} setOpen={setOpen} />
        <LogoDashboard open={open} />
        <MenuDashboard open={open} menus={menus} />
      </div>
      <div className="w-full">
        <NavbarDashboard />
        <main className="p-8">{children}</main>
      </div>
    </div>
  )
}
export default SidebarDashboard