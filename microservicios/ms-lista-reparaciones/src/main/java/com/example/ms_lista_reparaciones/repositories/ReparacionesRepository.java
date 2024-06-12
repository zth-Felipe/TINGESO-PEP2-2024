package com.example.ms_lista_reparaciones.repositories;

import com.example.ms_lista_reparaciones.entities.ReparacionesTipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparacionesRepository extends JpaRepository<ReparacionesTipoEntity, Integer> {
    public ReparacionesTipoEntity findByTipo(int tipo);
}
