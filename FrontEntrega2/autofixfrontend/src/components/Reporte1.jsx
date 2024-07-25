// src/components/Reporte1.js
import React, { useEffect, useState } from 'react';
import reporteService from '../services/reportes.service';

const Reporte1 = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    reporteService.getReporte1().then(response => {
      setData(response.data);
    }).catch(error => {
      console.error('Error fetching reporte1:', error);
    });
  }, []);

  return (
    <div>
      <h2>Reporte 1</h2>
      <table>
        <thead>
          <tr>
            <th>Tipo Reparación</th>
            <th>Nombre Reparación</th>
            <th>Sedan</th>
            <th>Sedan Cash</th>
            <th>Hatchback</th>
            <th>Hatchback Cash</th>
            <th>SUV</th>
            <th>SUV Cash</th>
            <th>Pickup</th>
            <th>Pickup Cash</th>
            <th>Furgoneta</th>
            <th>Furgoneta Cash</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.tipoRepa}</td>
              <td>{item.nombreReparacion}</td>
              <td>{item.sedan}</td>
              <td>{item.sedan_cash}</td>
              <td>{item.hatchback}</td>
              <td>{item.hatchback_cash}</td>
              <td>{item.suv}</td>
              <td>{item.suv_cash}</td>
              <td>{item.pickup}</td>
              <td>{item.pickup_cash}</td>
              <td>{item.furgoneta}</td>
              <td>{item.furgoneta_cash}</td>
              <td>{item.total}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Reporte1;
