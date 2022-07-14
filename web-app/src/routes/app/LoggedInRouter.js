import { Route, Routes } from 'react-router-dom';
import SidebarDashboard from '../../shared/layouts/dashboard/SidebarDashboard';
import HomeApp from '../../pages/private/HomeApp';

const LoggedInRouter = () => {
  return (
    <>
      <SidebarDashboard>
        <Routes>
          <Route path="home" element={<HomeApp />} />
        </Routes>
      </SidebarDashboard>
    </>
  )
}

export default LoggedInRouter