// src/components/HistorialDetalladoList.js
import React, { useEffect, useState } from "react";
import finanzasService from "../services/finanzas.service";


const HistorialDetalladoList = () => {
    const [patente, setPatente] = useState("");
    const [historialDetallado, setHistorialDetallado] = useState([]);

    const buscarHistorialDetallado = () => {
        finanzasService.historialPatenteDetallado(patente).then(response => {
            setHistorial(response.data);
        });
    };


    return (
        <div>
            <h2>Buscar Historial por Patente</h2>
            <input
                type="text"
                value={patente}
                onChange={(e) => setPatente(e.target.value)}
                placeholder="Ingrese patente"
            />
                
            <button onClick={buscarHistorialDetallado}>Buscar</button>
            <ul>
                <table>
                    <thead>
                        <tr>
                            <th>ID Historial</th>
                            <th>Patente</th>
                            <th>ID Reparacion</th>
                            <th>Fecha Reparacion</th>
                            <th>Hora Reparacion</th>
                            <th>Monto Reparacion</th>
                        </tr>
                    </thead>
                    <tbody>
                    {historialDetallado.map((item, index) => (
                        <tr key={index}>
                        <td>{item.idHistorial}</td>
                        <td>{item.patente}</td>
                        <td>{item.idReparacion}</td>
                        <td>{item.fechaReparacion}</td>
                        <td>{item.horaReparacion}</td>
                        <td>{item.montoReparacion}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </ul>
            
        </div>
      );

};

export default HistorialDetalladoList;
