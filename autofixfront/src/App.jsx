import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from './components/Home';
import RegistersView from './components/RegistrosView';
import RegistrosList from './components/RegistrosList';


function App() {
  return (
    <Router>
        <div className="container">
        <Navbar></Navbar>
          <Routes>
            <Route path='/home' element={<Home/>}/>
            <Route path='/registro' element={<RegistrosList/>}/>
            <Route path='/registro/:patente' element={<RegistersView/>}/>
            <Route path='*' element={<Home/>} />

          </Routes>
        </div>
    </Router>
  );
}

export default App
