import axios from 'axios';

export default axios.create({
    baseURL: `http://127.0.0.1:57633/`, // Cambia esta línea con la URL correcta
    headers: {
        "Content-type": "application/json"
    }
});
