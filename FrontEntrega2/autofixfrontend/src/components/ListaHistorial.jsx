import React, { useState, useEffect } from "react";
import finanzasService from "../services/finanzas.service";
import registroService from "../services/registro.service";

const HistorialTable = () => {
    const [historialData, setHistorialData] = useState([]);
    const [vehiculosData, setVehiculosData] = useState({});

    useEffect(() => {
        finanzasService.listaHistorial()
            .then(response => {
                setHistorialData(response.data);
                response.data.forEach(historial => {
                    registroService.getRegistro(historial.patente)
                        .then(res => {
                            setVehiculosData(prevData => ({
                                ...prevData,
                                [historial.patente]: res
                            }));
                        })
                        .catch(error => {
                            console.error(`Error fetching registro for patente ${historial.patente}:`, error);
                        });
                });
            })
            .catch(error => {
                console.error("Error fetching historial data:", error);
            });
    }, []);

    return (
        <div>
            <h2>Historial de Vehículos</h2>
            <table>
                <thead>
                    <tr>
                        <th>Patente Vehículo</th>
                        <th>Marca Vehículo</th>
                        <th>Modelo Vehículo</th>
                        <th>Tipo Vehículo</th>
                        <th>Año Fabricación</th>
                        <th>Tipo Motor</th>
                        <th>Fecha Ingreso Taller</th>
                        <th>Hora Ingreso Taller</th>
                        <th>Monto Total Reparaciones</th>
                        <th>Monto Recargos</th>
                        <th>Monto Dctos</th>
                        <th>SUB Total</th>
                        <th>Monto IVA</th>
                        <th>Costo Total</th>
                        <th>Fecha Salida Taller</th>
                        <th>Hora Salida Taller</th>
                        <th>Fecha Retiro Cliente</th>
                        <th>Hora Retiro Cliente</th>
                    </tr>
                </thead>
                <tbody>
                    {historialData.map(historial => {
                        const vehiculo = vehiculosData[historial.patente] || {};
                        return (
                            <tr key={historial.id}>
                                <td>{historial.patente}</td>
                                <td>{vehiculo.marca}</td>
                                <td>{vehiculo.modelo}</td>
                                <td>{vehiculo.tipo}</td>
                                <td>{vehiculo.anoFabr}</td>
                                <td>{vehiculo.tipoMotor}</td>
                                <td>{historial.fechaIngreso}</td>
                                <td>{historial.horaIngreso}</td>
                                <td>{historial.montoTotal}</td>
                                <td>{historial.montoRecargo}</td>
                                <td>{historial.montoDescuento}</td>
                                <td>{historial.montoTotal - historial.montoDescuento + historial.montoRecargo}</td>
                                <td>{historial.montoIva}</td>
                                <td>{historial.costoTotal}</td>
                                <td>{historial.fechaSalida}</td>
                                <td>{historial.horaSalida}</td>
                                <td>{historial.fechaCliente}</td>
                                <td>{historial.horaCliente}</td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
};

export default HistorialTable;