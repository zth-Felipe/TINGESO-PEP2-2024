package com.example.gestion_reparacion_autofix.repositories;

import com.example.gestion_reparacion_autofix.entities.ReparacionesTipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReparacionesRepository extends JpaRepository<ReparacionesTipoEntity, Integer> {
    public ReparacionesTipoEntity findByTipo(int tipo);
}
