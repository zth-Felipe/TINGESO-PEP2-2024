package com.autofix.msrepairVehicles.repositories;


import com.autofix.msrepairVehicles.entities.GenerateRepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface GenerateRepairsRepository extends JpaRepository<GenerateRepairsEntity, Long> {

    @Query(value = "SELECT V.marca, AVG(EXTRACT(EPOCH FROM (R.fecha_salida_reparacion - R.fecha_ingreso_taller)) / 3600) AS Promedio_Horas FROM Reparaciones R JOIN Vehiculos V ON R.patente_vehiculo = V.patente GROUP BY V.marca", nativeQuery = true)
    public List<Object[]> getPromedioHorasPorMarca();


    public GenerateRepairsEntity findById(int idReparacion);


    @Query(value = "SELECT rt.type AS nombre_reparacion, COUNT(DISTINCT CASE WHEN v.tipo = 'Sedan' THEN v.patente END) AS Sedan, COUNT(DISTINCT CASE WHEN v.tipo = 'Hatchback' THEN v.patente END) AS Hatchback, COUNT(DISTINCT CASE WHEN v.tipo = 'SUV' THEN v.patente END) AS SUV, COUNT(DISTINCT CASE WHEN v.tipo = 'Pickup' THEN v.patente END) AS Pickup, COUNT(DISTINCT CASE WHEN v.tipo = 'Furgoneta' THEN v.patente END) AS Furgoneta, COALESCE(SUM(CASE WHEN v.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN v.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN v.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN v.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END), 0) AS monto_total FROM repairs_type rt LEFT JOIN (SELECT r.patente_vehiculo, CAST(UNNEST(STRING_TO_ARRAY(r.tipo_reparacion, ',')) AS bigint) AS tipo_reparacion_individual FROM reparaciones r) sub ON rt.id = sub.tipo_reparacion_individual LEFT JOIN vehiculos v ON sub.patente_vehiculo = v.patente GROUP BY rt.type ORDER BY monto_total DESC", nativeQuery = true)
    public List<Object[]> GenerateGroupByTipoReparacion();


    @Query(value = "SELECT rt.type AS nombre_reparacion, COUNT(DISTINCT CASE WHEN v.tipo_motor = 'Gasolina' THEN v.patente END) AS vehiculos_gasolina, COUNT(DISTINCT CASE WHEN v.tipo_motor = 'Diesel' THEN v.patente END) AS vehiculos_diesel, COUNT(DISTINCT CASE WHEN v.tipo_motor = 'Híbrido' THEN v.patente END) AS vehiculos_hibrido, COUNT(DISTINCT CASE WHEN v.tipo_motor = 'Electrico' THEN v.patente END) AS vehiculos_electrico, COALESCE(SUM(CASE WHEN v.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN v.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN v.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN v.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END), 0) AS monto_total FROM repairs_type rt LEFT JOIN (SELECT r.patente_vehiculo, CAST(UNNEST(STRING_TO_ARRAY(r.tipo_reparacion, ',')) AS bigint) AS tipo_reparacion_individual FROM reparaciones r) sub ON rt.id = sub.tipo_reparacion_individual LEFT JOIN vehiculos v ON sub.patente_vehiculo = v.patente GROUP BY rt.type ORDER BY monto_total DESC", nativeQuery = true)
    public List<Object[]> GenerateGroupByCombustible();


    @Query(value = "SELECT COUNT(*) AS cantidad_reparaciones FROM reparaciones WHERE patente_vehiculo = :patente AND fecha_salida_reparacion >= CURRENT_DATE - INTERVAL '1 year'", nativeQuery = true)
    public int getCountReparacionesByPatenteUnaño(@Param("patente") String patente);

    public ArrayList<GenerateRepairsEntity> findAll();

    @Query(value = "SELECT rt.nombre AS nombre_reparacion, COUNT(DISTINCT CASE WHEN vt.tipo = 'Sedan' THEN vt.patente END) AS Sedan, COALESCE(SUM(CASE WHEN vt.tipo = 'Sedan' THEN CASE WHEN vt.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN vt.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN vt.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN vt.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END ELSE 0 END), 0) AS monto_total_sedan, COUNT(DISTINCT CASE WHEN vt.tipo = 'Hatchback' THEN vt.patente END) AS Hatchback, COALESCE(SUM(CASE WHEN vt.tipo = 'Hatchback' THEN CASE WHEN vt.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN vt.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN vt.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN vt.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END ELSE 0 END), 0) AS monto_total_hatchback, COUNT(DISTINCT CASE WHEN vt.tipo = 'SUV' THEN vt.patente END) AS SUV, COALESCE(SUM(CASE WHEN vt.tipo = 'SUV' THEN CASE WHEN vt.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN vt.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN vt.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN vt.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END ELSE 0 END), 0) AS monto_total_suv, COUNT(DISTINCT CASE WHEN vt.tipo = 'Pickup' THEN vt.patente END) AS Pickup, COALESCE(SUM(CASE WHEN vt.tipo = 'Pickup' THEN CASE WHEN vt.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN vt.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN vt.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN vt.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END ELSE 0 END), 0) AS monto_total_pickup, COUNT(DISTINCT CASE WHEN vt.tipo = 'Furgoneta' THEN vt.patente END) AS Furgoneta, COALESCE(SUM(CASE WHEN vt.tipo = 'Furgoneta' THEN CASE WHEN vt.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN vt.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN vt.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN vt.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END ELSE 0 END), 0) AS monto_total_furgoneta, COALESCE(SUM(CASE WHEN vt.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN vt.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN vt.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN vt.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END), 0) AS monto_total, COUNT(sub.reparacion_id) AS cantidad_reparaciones FROM reparacion_temp rt LEFT JOIN (SELECT r.id AS reparacion_id, r.patente_vehiculo, UNNEST(STRING_TO_ARRAY(r.tipo_reparacion, ',')) AS tipo_reparacion_individual FROM reparaciones r) sub ON rt.id = CAST(sub.tipo_reparacion_individual AS bigint) LEFT JOIN vehicle_temp vt ON sub.patente_vehiculo = vt.patente GROUP BY rt.nombre ORDER BY monto_total DESC", nativeQuery = true)
    public List<Object[]> GenerateReport1();

    @Query(value = "WITH reparaciones_filtradas AS (SELECT rt.nombre AS nombre_reparacion, EXTRACT(MONTH FROM sub.fecha_ingreso_taller) AS mes, COUNT(sub.reparacion_id) AS cantidad_reparaciones, COALESCE(SUM(CASE WHEN vt.tipo_motor = 'Diesel' THEN rt.cost_diesel WHEN vt.tipo_motor = 'Electrico' THEN rt.cost_electric WHEN vt.tipo_motor = 'Gasolina' THEN rt.cost_gasoline WHEN vt.tipo_motor = 'Híbrido' THEN rt.cost_hybrid ELSE 0 END), 0) AS monto_total FROM reparacion_temp rt LEFT JOIN (SELECT r.id AS reparacion_id, r.patente_vehiculo, r.fecha_ingreso_taller, UNNEST(STRING_TO_ARRAY(r.tipo_reparacion, ',')) AS tipo_reparacion_individual FROM reparaciones r) sub ON rt.id = CAST(sub.tipo_reparacion_individual AS bigint) LEFT JOIN vehicle_temp vt ON sub.patente_vehiculo = vt.patente WHERE EXTRACT(YEAR FROM sub.fecha_ingreso_taller) = :year AND EXTRACT(MONTH FROM sub.fecha_ingreso_taller) IN (:selectedMonth, :prevMonth, :nextMonth) GROUP BY rt.nombre, EXTRACT(MONTH FROM sub.fecha_ingreso_taller)) SELECT rf.nombre_reparacion, COALESCE(MAX(CASE WHEN rf.mes = :selectedMonth THEN rf.cantidad_reparaciones END), 0) AS cantidad_reparaciones_selected, COALESCE(MAX(CASE WHEN rf.mes = :selectedMonth THEN rf.monto_total END), 0) AS monto_total_selected, COALESCE(MAX(CASE WHEN rf.mes = :prevMonth THEN rf.cantidad_reparaciones END), 0) AS cantidad_reparaciones_prev1, COALESCE(MAX(CASE WHEN rf.mes = :prevMonth THEN rf.monto_total END), 0) AS monto_total_prev1, COALESCE(MAX(CASE WHEN rf.mes = :nextMonth THEN rf.cantidad_reparaciones END), 0) AS cantidad_reparaciones_prev2, COALESCE(MAX(CASE WHEN rf.mes = :nextMonth THEN rf.monto_total END), 0) AS monto_total_prev2 FROM reparaciones_filtradas rf GROUP BY rf.nombre_reparacion ORDER BY rf.nombre_reparacion", nativeQuery = true)
    List<Object[]> GenerateReport2(
            @Param("year") int year,
            @Param("selectedMonth") int selectedMonth,
            @Param("prevMonth") int prevMonth,
            @Param("nextMonth") int nextMonth);



}
