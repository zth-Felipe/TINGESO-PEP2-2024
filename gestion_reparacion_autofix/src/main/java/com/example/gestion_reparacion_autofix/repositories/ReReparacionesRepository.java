package com.example.gestion_reparacion_autofix.repositories;

import com.example.gestion_reparacion_autofix.entities.ReReparacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReReparacionesRepository extends JpaRepository<ReReparacionesEntity, Integer> {
    public ReReparacionesEntity findByTipoRepa(int tipoRepa);
}
