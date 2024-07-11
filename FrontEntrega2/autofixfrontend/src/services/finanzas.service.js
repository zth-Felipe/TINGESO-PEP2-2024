import axios from "axios";

const FINANZAS_API_URL = "http://localhost:8090/api/finanzas/";

function obtenerListaPrecios(patente, fechaingreso, fechasalida, fechacliente, kilometraje){
    return axios.post(FINANZAS_API_URL, patente, fechaingreso, fechasalida, fechacliente, kilometraje);
}