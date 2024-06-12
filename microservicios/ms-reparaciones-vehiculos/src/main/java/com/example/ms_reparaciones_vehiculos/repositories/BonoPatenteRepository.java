package com.example.ms_reparaciones_vehiculos.repositories;

import com.example.ms_reparaciones_vehiculos.entities.BonoPatenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonoPatenteRepository extends JpaRepository<BonoPatenteEntity, String> {

    public BonoPatenteEntity findByPatente(String patente);
    public BonoPatenteEntity findByPatenteAndMesAndAno(String patente, int mes, int ano);
    List<BonoPatenteEntity> findAllByPatente(String patente);

}
