// src/components/AddReparacion.js
import React, { useState } from "react";
import listareparacionesService from "../services/listareparaciones.service";


const AddReparacion = () => {
    const [formData, setFormData] = useState({
        tipo: 0,
        nombreRepa: "",
        gasolina: 0,
        diesel: 0,
        hibrido: 0,
        electrico: 0
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        listareparacionesService.saveReparacion(formData).then(response => {
            console.log("Reparación agregada:", response.data);
        }).catch(error => {
            console.error("Error al agregar reparación:", error);
        });
    };

    return (
        <div>
            <h2>Agregar Nueva Reparación</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="number"
                    name="tipo"
                    value={formData.tipo}
                    onChange={handleChange}
                    placeholder="Tipo"
                />
                <input
                    type="text"
                    name="nombreRepa"
                    value={formData.nombreRepa}
                    onChange={handleChange}
                    placeholder="Nombre de la Reparación"
                />
                <input
                    type="number"
                    name="gasolina"
                    value={formData.gasolina}
                    onChange={handleChange}
                    placeholder="Gasolina"
                />
                <input
                    type="number"
                    name="diesel"
                    value={formData.diesel}
                    onChange={handleChange}
                    placeholder="Diesel"
                />
                <input
                    type="number"
                    name="hibrido"
                    value={formData.hibrido}
                    onChange={handleChange}
                    placeholder="Híbrido"
                />
                <input
                    type="number"
                    name="electrico"
                    value={formData.electrico}
                    onChange={handleChange}
                    placeholder="Eléctrico"
                />
                <button type="submit">Agregar Reparación</button>
            </form>
        </div>
    );
};

export default AddReparacion;
