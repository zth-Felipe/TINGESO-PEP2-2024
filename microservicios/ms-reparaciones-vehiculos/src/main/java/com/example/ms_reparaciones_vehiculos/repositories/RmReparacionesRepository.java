package com.example.ms_reparaciones_vehiculos.repositories;

import com.example.ms_reparaciones_vehiculos.entities.RmReparacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RmReparacionesRepository extends JpaRepository<RmReparacionesEntity, Integer> {
    public RmReparacionesEntity findByTipoRepa(int tipoRepa);
}
