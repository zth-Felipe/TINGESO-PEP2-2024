import axios from "axios";

const REPORTES_API_URL = "http://localhost:8093/api/reportes/";

function getReporte1() {
    return axios.get(`${REPORTES_API_URL}report1`);
}

function getReporte2(year, month) {
    return axios.get(`${REPORTES_API_URL}report2/${year}/${month}`);
}

export default {getReporte1, getReporte2}