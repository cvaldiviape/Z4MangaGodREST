import './App.css';
import 'boxicons/css/boxicons.min.css';
import { Provider } from 'react-redux';
import AppRouter from './routes/AppRouter';
import store from './redux/store';

function App() {
  return (
    <>
      <Provider store={store}>
        <AppRouter />
      </Provider>
    </>
  );
}

export default App;
