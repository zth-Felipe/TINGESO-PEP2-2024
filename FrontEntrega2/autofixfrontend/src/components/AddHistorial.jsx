// src/components/AddHistorial.js
import React, { useState } from "react";
import finanzasService from "../services/finanzas.service";

const AddHistorial = () => {
    const [formData, setFormData] = useState({
        patente: "",
        fechaIngreso: "",
        horaIngreso: "",
        montoTotal: 0,
        montoDescuento: 0,
        montoRecargo: 0,
        montoIva: 0,
        costoTotal: 0,
        fechaSalida: "",
        horaSalida: "",
        fechaCliente: "",
        horaCliente: ""
    });

    const [bono, setBono] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleBonoChange = (e) => {
        setBono(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        finanzasService.saveHistorial(formData, bono).then(response => {
            console.log("Historial agregado:", response.data);
        }).catch(error => {
            console.error("Error al agregar historial:", error);
        });
    };

    return (
        <div>
            <h2>Agregar Nuevo Historial</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Patente:</label>
                    <input
                        type="text"
                        name="patente"
                        value={formData.patente}
                        onChange={handleChange}
                        placeholder="Patente del vehículo"
                    />
                </div>
                <div>
                    <label>Fecha de Ingreso:</label>
                    <input
                        type="date"
                        name="fechaIngreso"
                        value={formData.fechaIngreso}
                        onChange={handleChange}
                        placeholder="Ingrese la fecha de ingreso"
                    />
                </div>
                <div>
                    <label>Hora de Ingreso:</label>
                    <input
                        type="time"
                        name="horaIngreso"
                        value={formData.horaIngreso}
                        onChange={handleChange}
                        placeholder="Ingrese la hora de ingreso"
                    />
                </div>
                <div>
                    <label>Fecha de Salida:</label>
                    <input
                        type="date"
                        name="fechaSalida"
                        value={formData.fechaSalida}
                        onChange={handleChange}
                        placeholder="Ingrese la fecha de salida"
                    />
                </div>
                <div>
                    <label>Hora de Salida:</label>
                    <input
                        type="time"
                        name="horaSalida"
                        value={formData.horaSalida}
                        onChange={handleChange}
                        placeholder="Ingrese la hora de salida"
                    />
                </div>
                <div>
                    <label>Fecha de Atención al Cliente:</label>
                    <input
                        type="date"
                        name="fechaCliente"
                        value={formData.fechaCliente}
                        onChange={handleChange}
                        placeholder="Ingrese la fecha de atención al cliente"
                    />
                </div>
                <div>
                    <label>Hora de Atención al Cliente:</label>
                    <input
                        type="time"
                        name="horaCliente"
                        value={formData.horaCliente}
                        onChange={handleChange}
                        placeholder="Ingrese la hora de atención al cliente"
                    />
                </div>
                <div>
                    <label>Bono:</label>
                    <input
                        type="text"
                        value={bono}
                        onChange={handleBonoChange}
                        placeholder="Ingrese el bono"
                    />
                </div>
                <p></p>
                <button type="submit">Agregar Historial</button>
            </form>
        </div>
    );


};

export default AddHistorial;
