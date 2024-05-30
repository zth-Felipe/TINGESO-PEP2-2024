package com.example.ms_vehiculos.repositories;

import com.example.ms_vehiculos.entities.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
    public RegistroEntity findByPatente(String patente);
}
