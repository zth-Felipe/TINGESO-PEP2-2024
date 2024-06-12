package com.example.ms_reparaciones_vehiculos.repositories;

import com.example.ms_reparaciones_vehiculos.entities.ReparacionesTempEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparacionesTempRepository extends JpaRepository<ReparacionesTempEntity, Integer> {
    public ReparacionesTempEntity findByTipo(int tipo);
}
