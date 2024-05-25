import { useEffect, useState } from "react"
import registroService from "../services/registro.service.js";
//import gestionService from "../services/gestion.service.js";
import { Link } from "react-router-dom";

export default function RegistrosList() {
    const [registros, setRegistros] = useState([]);
  
    async function fetchRegistros() {
      try{      
        const response = await registroService.getRegistros();  
        setRegistros(response.data);
      }catch(error) {
        alert("Error al obtener los registros.");
      }
    }
    
    useEffect(() => {
      fetchRegistros();
    }, [])
  
    return (
      <div className="container">
        <h1>Lista registros</h1>
    
        <table className="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Patente</th>
              <th scope="col">Marca</th>
              <th scope="col">Modelo</th>
              <th scope="col">Tipo</th>
              <th scope="col">Año Fabricación</th>
              <th scope="col">Tipo Motor</th>
              <th scope="col">Numero de Asientos</th>
            </tr>
          </thead>
          <tbody>        
            {                                 
              registros.map((registro, index) => (
                <tr key={index}>
                  <td>{registro.id}</td>
                  <td>{registro.patente}</td>
                  <td>{registro.marca}</td>
                  <td>{registro.modelo}</td>
                  <td>{registro.tipo}</td>
                  <td>{registro.anoFabr}</td>
                  <td>{registro.tipoMotor}</td>
                  <td>{registro.numAsientos}</td>
                  <td>
                    <Link to={`/registros/${registro.rut}`}>Ver</Link>
                  </td>
                </tr>
              ))
            }
          </tbody>
        </table>
      </div>   
    )
  }