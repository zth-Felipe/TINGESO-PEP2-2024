package com.autofix.msvehicles.repositories;

import com.autofix.msvehicles.entities.vehiclesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface vehiclesRepository extends JpaRepository<vehiclesEntity, Long> {

    vehiclesEntity findByPatente(String patente);

    public List<vehiclesEntity> findAll();

    public void deleteById(long id);

    @Query(value = "SELECT V.patente FROM vehiclesEntity V")
    String[] findAllPatente();

}
