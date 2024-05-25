package com.example.gestion_reparacion_autofix.repositories;

import com.example.gestion_reparacion_autofix.entities.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
    public RegistroEntity findByPatente(String patente);
}
