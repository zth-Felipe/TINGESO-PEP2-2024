// src/services/reparacionesService.js
import axios from 'axios';

const LISTAREPA_API_URL = 'http://localhost:8090/api/reparaciones/';

function getReparaciones() {
  return axios.get(LISTAREPA_API_URL);
}

function getReparacion(id) {
  return axios.get(`${LISTAREPA_API_URL}${id}`);
}

function saveReparacion(reparacion) {
  return axios.post(LISTAREPA_API_URL, reparacion);
}

function setReparacion(reparacion) {
  return axios.get(`${LISTAREPA_API_URL}modificar`, reparacion);
}


export default { getReparaciones, getReparacion, saveReparacion, setReparacion};
