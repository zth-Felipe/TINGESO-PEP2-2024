package com.example.gestion_reparacion_autofix.repositories;

import com.example.gestion_reparacion_autofix.entities.BonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonoRepository extends JpaRepository<BonoEntity, String> {
    public BonoEntity findByMarca(String marca);

    public BonoEntity findByMarcaAndMesAndAno(String marca, int mes, int ano);

}
