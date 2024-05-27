package com.example.servicesvehiculos.repositories;

import com.example.servicesvehiculos.entities.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
    public RegistroEntity findByPatente(String patente);
}
