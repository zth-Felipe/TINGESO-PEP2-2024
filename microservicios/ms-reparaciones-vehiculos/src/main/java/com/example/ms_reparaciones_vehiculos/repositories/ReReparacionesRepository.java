package com.example.ms_reparaciones_vehiculos.repositories;

import com.example.ms_reparaciones_vehiculos.entities.ReReparacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReReparacionesRepository extends JpaRepository<ReReparacionesEntity, Integer> {
    public ReReparacionesEntity findByTipoRepa(int tipoRepa);
}
