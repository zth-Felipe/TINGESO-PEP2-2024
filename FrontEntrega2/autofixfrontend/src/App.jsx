import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from './components/Home';
import ListaReparaciones from './components/ListaReparaciones';
import DetalleReparacion from './components/DetalleReparacion';
import RegistersView from './components/RegistrosView';
import RegistrosList from './components/RegistrosList';
import Reporte1 from './components/Reporte1';
import Reporte2 from './components/Reporte2';
import CrearRegistro from './components/CrearRegistro';
import HistorialPatente from './components/HistorialPatente';
import AddReparacion from './components/AddReparacion';
import UpdateReparacion from './components/UpdateReparacion';
import AddHistorialDetallado from './components/AddHistorialDetallado';
import HistorialDetalladoList from './components/HistorialDetalladoList';
import AddHistorial from './components/AddHistorial';
import HistorialTable from './components/ListaHistorial';


function App() {
  return (
    <Router>
        <div className="container">
        <Navbar></Navbar>
          <Routes>
            <Route path='/home' element={<Home/>}/>
            <Route path='/registro' element={<RegistrosList/>}/>
            <Route path='/registro/:patente' element={<RegistersView/>}/>
            <Route path='/crear-registro' element={<CrearRegistro/>}/>
            <Route path='/reporte1' element={<Reporte1/>}/>
            <Route path='/reporte2' element={<Reporte2/>}/>
            <Route path='/listareparaciones' element={<ListaReparaciones/>}/>
            <Route path='/detallereparacion' element={<DetalleReparacion/>}/>
            <Route path='/historial-patente' element={<HistorialPatente/>}/>
            <Route path='/addreparacion' element={<AddReparacion/>}/>
            <Route path='/updatereparacion' element={<UpdateReparacion/>}/>
            <Route path='/nuevo-historial-d' element={<AddHistorialDetallado/>}/>
            <Route path='/historial-detallado-patente' element={<HistorialDetalladoList/>}/>
            <Route path='/nuevo-historial' element={<AddHistorial/>}/>
            <Route path='/AllHistorials' element={<HistorialTable/>}/>
            <Route path='*' element={<Home/>} />

          </Routes>
        </div>
    </Router>
  );
}
export default App
