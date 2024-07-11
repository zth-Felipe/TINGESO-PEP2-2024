package com.example.ms_reparaciones_vehiculos.repositories;

import com.example.ms_reparaciones_vehiculos.entities.RtReparacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RtReparacionesRepository extends JpaRepository<RtReparacionesEntity, Integer> {
    public RtReparacionesEntity findByTipoRepa(int tipoRepa);
    public RtReparacionesEntity findByTipoRepaAndAnoAndMes(int tipoRepa, int ano, int mes);
    public RtReparacionesEntity findByAnoAndMes(int ano, int mes);
    public List<RtReparacionesEntity> findAllByAnoAndMes(int ano, int mes);
}
