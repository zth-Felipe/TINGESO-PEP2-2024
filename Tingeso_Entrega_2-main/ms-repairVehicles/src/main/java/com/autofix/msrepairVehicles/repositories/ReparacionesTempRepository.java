package com.autofix.msrepairVehicles.repositories;

import com.autofix.msrepairVehicles.entities.ReparacionTempEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReparacionesTempRepository extends JpaRepository<ReparacionTempEntity, Long> {
}
