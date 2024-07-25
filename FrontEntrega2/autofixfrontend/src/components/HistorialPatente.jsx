// src/components/HistorialPatente.js
import React, { useState } from "react";
import finanzasService from "../services/finanzas.service";
const HistorialPatente = () => {
    const [patente, setPatente] = useState("");
    const [historial, setHistorial] = useState([]);

    const buscarHistorial = () => {
        finanzasService.historialPatente(patente).then(response => {
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
                
            <button onClick={buscarHistorial}>Buscar</button>
            <ul>
                <table>
                    <thead>
                        <tr>
                            <th>Patente</th>
                            <th>Fecha Ingreso</th>
                            <th>Hora Ingreso</th>
                            <th>montoTotal</th>
                            <th>montoDescuento</th>
                            <th>montoRecargo</th>
                            <th>montoIva</th>
                            <th>costoTotal</th>
                            <th>fechaSalida</th>
                            <th>horaSalida</th>
                            <th>fechaCliente</th>
                            <th>horaCliente</th>
                        </tr>
                    </thead>
                    <tbody>
                    {historial.map((item, index) => (
                        <tr key={index}>
                        <td>{item.patente}</td>
                        <td>{item.fechaIngreso}</td>
                        <td>{item.horaIngreso}</td>
                        <td>{item.montoTotal}</td>
                        <td>{item.montoDescuento}</td>
                        <td>{item.montoRecargo}</td>
                        <td>{item.montoIva}</td>
                        <td>{item.costoTotal}</td>
                        <td>{item.fechaSalida}</td>
                        <td>{item.horaSalida}</td>
                        <td>{item.fechaCliente}</td>
                        <td>{item.horaCliente}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </ul>
            
        </div>
      );

};

export default HistorialPatente;
