package com.example.ms_reparaciones_vehiculos.repositories;

import com.example.ms_reparaciones_vehiculos.entities.BonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonoRepository extends JpaRepository<BonoEntity, String> {
    public BonoEntity findByMarca(String marca);

    public BonoEntity findByMarcaAndMesAndAno(String marca, int mes, int ano);

}
