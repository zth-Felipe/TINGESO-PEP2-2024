import React, { useEffect, useState } from 'react';
import reparacionesService from '../services/listareparaciones.service';

const ListaReparaciones = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    reparacionesService.getReparaciones().then(response => {
      setData(response.data);
    }).catch(error => {
      console.error('Error fetching reparaciones:', error);
    });
  }, []);

  return (
    <div>
      <h2>Lista de Reparaciones</h2>
      <table>
        <thead>
          <tr>
            <th>Tipo</th>
            <th>Nombre</th>
            <th>Gasolina</th>
            <th>Diesel</th>
            <th>Híbrido</th>
            <th>Eléctrico</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.tipo}</td>
              <td>{item.nombreRepa}</td>
              <td>{item.gasolina}</td>
              <td>{item.diesel}</td>
              <td>{item.hibrido}</td>
              <td>{item.electrico}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListaReparaciones;