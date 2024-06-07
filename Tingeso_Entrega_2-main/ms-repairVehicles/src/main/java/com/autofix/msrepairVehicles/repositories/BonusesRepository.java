package com.autofix.msrepairVehicles.repositories;

import com.autofix.msrepairVehicles.entities.BonusesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BonusesRepository extends JpaRepository<BonusesEntity, Long> {

    public BonusesEntity findByMarca(String marca);

    public BonusesEntity findById(long id);

    public ArrayList<BonusesEntity> findAll();


}
