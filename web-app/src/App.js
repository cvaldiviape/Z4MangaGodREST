import './App.css';
import 'boxicons/css/boxicons.min.css';
import { Provider } from 'react-redux';
import AppRouter from './routes/AppRouter';
import store, { persistor } from './redux/store';
import { PersistGate } from 'redux-persist/integration/react'

function App() {
  return (
    <>
      <Provider store={store}>
        <PersistGate persistor={persistor}>
          <AppRouter />
        </PersistGate>
      </Provider>
    </>
  );
}

export default App;
