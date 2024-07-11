package com.example.ms_reparaciones_vehiculos.repositories;


import com.example.ms_reparaciones_vehiculos.entities.HistorialDetalladoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialDetalladoRepository extends JpaRepository<HistorialDetalladoEntity, Long> {
    public List<HistorialDetalladoEntity> findAllByPatenteAndFechaReparacionBeforeAndFechaReparacionAfter(String patente, LocalDate before, LocalDate after);
    public List<HistorialDetalladoEntity> findAllByPatente(String patente);
}
