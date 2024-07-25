// src/components/Reporte2.js
import React, { useState } from 'react';
import reporteService from '../services/reportes.service';

const Reporte2 = () => {
  const [year, setYear] = useState('');
  const [month, setMonth] = useState('');
  const [data, setData] = useState([]);

  const fetchReport = () => {
    reporteService.getReporte2(year, month).then(response => {
      setData(response.data);
    }).catch(error => {
      console.error('Error fetching reporte2:', error);
    });
  };

  return (
    <div>
      <h2>Reporte 2</h2>
      <div>
        <input
          type="number"
          placeholder="Año"
          value={year}
          onChange={(e) => setYear(e.target.value)}
        />
        <input
          type="number"
          placeholder="Mes"
          value={month}
          onChange={(e) => setMonth(e.target.value)}
        />
        <button onClick={fetchReport}>Obtener Reporte</button>
      </div>
      <table>
        <thead>
          <tr>
            <th>Tipo Reparación</th>
            <th>Mes Actual</th>
            <th>Cantidad Actual</th>
            <th>Monto Actual</th>
            <th>Mes Previo 1</th>
            <th>Cantidad Previa 1</th>
            <th>Monto Previo 1</th>
            <th>Mes Previo 2</th>
            <th>Cantidad Previa 2</th>
            <th>Monto Previo 2</th>
            <th>Variación Cantidad Mes 1</th>
            <th>Variación Monto Mes 1</th>
            <th>Variación Cantidad Mes 2</th>
            <th>Variación Monto Mes 2</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.tipoRepa}</td>
              <td>{item.mesActual}</td>
              <td>{item.cantidadRepaActual}</td>
              <td>{item.montoActual}</td>
              <td>{item.mesPrevio1}</td>
              <td>{item.cantidadRepaPrevio1}</td>
              <td>{item.montoPrevio1}</td>
              <td>{item.mesPrevio2}</td>
              <td>{item.cantidadRepaPrevio2}</td>
              <td>{item.montoPrevio2}</td>
              <td>{item.variacionCantidadMes1}</td>
              <td>{item.variacionMontoMes1}</td>
              <td>{item.variacionCantidadMes2}</td>
              <td>{item.variacionMontoMes2}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Reporte2;
