package com.example.ms_reparaciones_vehiculos.repositories;

import com.example.ms_reparaciones_vehiculos.entities.HistorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<HistorialEntity, Long> {

    public HistorialEntity findByFechaCliente(LocalDate fechacliente);
    public HistorialEntity findByFechaIngreso(LocalDate fechaingreso);
    public HistorialEntity findByFechaSalida(LocalDate fechasalida);
    List<HistorialEntity> findByPatente(String patente);

    List<HistorialEntity> findAllByPatente(String patente);

    List<HistorialEntity> findAllByPatenteAndFechaIngresoAndFechaCliente(String patente, LocalDate fechaingreso, LocalDate fechacliente);
    public HistorialEntity findByPatenteAndFechaIngresoAndFechaCliente(String patente, LocalDate fechaingreso, LocalDate fechacliente);


}
