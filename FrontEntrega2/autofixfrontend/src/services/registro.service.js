import axios from "axios";

const REGISTROS_API_URL = "http://localhost:8092/api/register/";

function crearRegistro(registro) {
    return axios.post(REGISTROS_API_URL, registro);
}

function getRegistros() {
    return axios.get(REGISTROS_API_URL);
}

function getRegistro(patente) {
    return axios.get(`${REGISTROS_API_URL}${patente}`).then(response => {
        return response.data;
    });
}
export default {crearRegistro, getRegistro, getRegistros}