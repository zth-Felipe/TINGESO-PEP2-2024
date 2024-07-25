// src/components/CrearRegistro.js
import React, { useState } from 'react';
import registroService from '../services/registro.service';

const CrearRegistro = () => {
  const [formData, setFormData] = useState({
    patente: '',
    marca: '',
    modelo: '',
    tipo: '',
    anoFabr: '',
    tipoMotor: '',
    numAsientos: '',
    bono: '',
    kilometraje: ''
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    registroService.crearRegistro(formData)
      .then(response => {
        setMessage('Registro creado con éxito!');
        setFormData({
          patente: '',
          marca: '',
          modelo: '',
          tipo: '',
          anoFabr: '',
          tipoMotor: '',
          numAsientos: '',
          bono: '',
          kilometraje: ''
        });
      })
      .catch(error => {
        setMessage('Error al crear el registro');
        console.error('Error:', error);
      });
  };

  return (
    <div>
      <h2>Crear Registro</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Patente:</label>
          <input type="text" name="patente" value={formData.patente} onChange={handleChange} required />
        </div>
        <div>
          <label>Marca:</label>
          <input type="text" name="marca" value={formData.marca} onChange={handleChange} required />
        </div>
        <div>
          <label>Modelo:</label>
          <input type="text" name="modelo" value={formData.modelo} onChange={handleChange} required />
        </div>
        <div>
          <label>Tipo:</label>
          <input type="text" name="tipo" value={formData.tipo} onChange={handleChange} required />
        </div>
        <div>
          <label>Año de Fabricación:</label>
          <input type="number" name="anoFabr" value={formData.anoFabr} onChange={handleChange} required />
        </div>
        <div>
          <label>Tipo de Motor:</label>
          <input type="text" name="tipoMotor" value={formData.tipoMotor} onChange={handleChange} required />
        </div>
        <div>
          <label>Número de Asientos:</label>
          <input type="number" name="numAsientos" value={formData.numAsientos} onChange={handleChange} required />
        </div>
        <div>
          <label>Bono:</label>
          <input type="text" name="bono" value={formData.bono} onChange={handleChange} required />
        </div>
        <div>
          <label>Kilometraje:</label>
          <input type="number" name="kilometraje" value={formData.kilometraje} onChange={handleChange} required />
        </div>
        <button type="submit">Crear</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default CrearRegistro;