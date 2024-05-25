package com.example.gestion_reparacion_autofix.repositories;

import com.example.gestion_reparacion_autofix.entities.RmReparacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RmReparacionesRepository extends JpaRepository<RmReparacionesEntity, Integer> {
    public RmReparacionesEntity findByTipoRepa(int tipoRepa);
}
