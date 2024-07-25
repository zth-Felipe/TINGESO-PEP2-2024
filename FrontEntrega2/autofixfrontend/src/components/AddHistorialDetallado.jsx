// src/components/AddHistorialDetallado.js
import React, { useState } from "react";
import finanzasService from "../services/finanzas.service";

const AddHistorialDetallado = () => {
    const [formData, setFormData] = useState({
        idHistorial: 0,
        patente: "",
        idReparacion: 0,
        fechaReparacion: "",
        horaReparacion: "",
        montoReparacion: 0
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        finanzasService.saveHistorialDetallado(formData).then(response => {
            console.log("Historial Detallado agregado:", response.data);
        }).catch(error => {
            console.error("Error al agregar Historial Detallado:", error);
        });
    };

    return (
        <div>
            <h2>Agregar Nuevo Historial Detallado para un Vehiculo</h2>
            <form onSubmit={handleSubmit}>

                
                <input
                    type="text"
                    name="patente"
                    value={formData.patente}
                    onChange={handleChange}
                    placeholder="Patente"
                />
                <input
                    type="number"
                    name="idReparacion"
                    value={formData.idReparacion}
                    onChange={handleChange}
                    placeholder="ID Reparación"
                />
                <input
                    type="date"
                    name="fechaReparacion"
                    value={formData.fechaReparacion}
                    onChange={handleChange}
                    placeholder="Fecha de Reparación"
                />
                <input
                    type="time"
                    name="horaReparacion"
                    value={formData.horaReparacion}
                    onChange={handleChange}
                    placeholder="Hora de Reparación"
                />
                <input
                    type="number"
                    name="montoReparacion"
                    value={formData.montoReparacion}
                    onChange={handleChange}
                    placeholder="Monto de Reparación"
                />
                <button type="submit">Agregar Historial Detallado</button>
            </form>
        </div>
    );
};

export default AddHistorialDetallado;
