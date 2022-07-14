import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import PublicRoute from './PublicRoute';
import PrivateRoute from './PrivateRoute';
import LoggedOutRouter from './app/LoggedOutRouter';
import LoggedInRouter from './app/LoggedInRouter';
import { useSelector } from 'react-redux';

const AppRouter = () => {
  const { responseData } = useSelector(state => state.auth);

  return (
    <>   
      <BrowserRouter>
        <Routes>

          {/* ruta inicial, es como un "home public", que cualquier usuario puede ver */}
          <Route path="/*" element={<Navigate to="/auth/" replace />} />

          {/* aqui administramos las rutas publicas */}
          <Route 
            path="/auth/*" 
            element={
              <PublicRoute isAuth={responseData.token}>
                <LoggedOutRouter />
              </PublicRoute>
            } 
          />

          {/* aqui administramos las rutas privadas */}
          <Route 
            path="/app/*" 
            element={
              <PrivateRoute isAuth={responseData.token}>
                <LoggedInRouter />
              </PrivateRoute>
            } 
          />

        </Routes>
      </BrowserRouter>
    </>
  )
}

export default AppRouter