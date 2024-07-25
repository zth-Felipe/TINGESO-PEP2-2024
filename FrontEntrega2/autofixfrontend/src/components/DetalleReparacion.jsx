// src/components/DetalleReparacion.js
import React, { useState } from 'react';
import reparacionesService from '../services/listareparaciones.service';

const DetalleReparacion = () => {
  const [id, setId] = useState('');
  const [data, setData] = useState(null);

  const fetchReparacion = () => {
    reparacionesService.getReparacion(id).then(response => {
      setData(response.data);
    }).catch(error => {
      console.error('Error fetching reparacion:', error);
    });
  };

  return (
    <div>
      <h2>Detalle de Reparación</h2>
      <div>
        <input
          type="number"
          placeholder="ID"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
        <button onClick={fetchReparacion}>Obtener Detalles</button>
      </div>
      {data && (
        <div>
          <h3>Tipo: {data.tipo}</h3>
          <p>Gasolina: {data.gasolina}</p>
          <p>Diesel: {data.diesel}</p>
          <p>Híbrido: {data.hibrido}</p>
          <p>Eléctrico: {data.electrico}</p>
        </div>
      )}
    </div>
  );
};

export default DetalleReparacion;
