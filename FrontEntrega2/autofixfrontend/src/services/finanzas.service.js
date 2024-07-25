import axios from "axios";

const FINANZAS_API_URL = "http://localhost:8091/api/historial/";

function listaHistorial(){
    return axios.get(FINANZAS_API_URL);
}

function listaHistorialDetallado(){
    return axios.get(`${FINANZAS_API_URL}detallado`);
}

function historialPatente(patente){
    return axios.get(`${FINANZAS_API_URL}${patente}`);
}

function historialPatenteDetallado(patente){
    return axios.get(`${FINANZAS_API_URL}detallado/${patente}`);
}

function saveHistorial(historial, bono){
    return axios.post(`${FINANZAS_API_URL}${bono}`, historial);
}

function saveHistorialDetallado(historialD){
    return axios.post(`${FINANZAS_API_URL}detallado`, historialD);
}


  
export default {listaHistorial, listaHistorialDetallado, historialPatenteDetallado, historialPatente, saveHistorial, saveHistorialDetallado}