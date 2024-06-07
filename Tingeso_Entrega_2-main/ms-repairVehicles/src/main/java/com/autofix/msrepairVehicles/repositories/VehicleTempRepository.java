package com.autofix.msrepairVehicles.repositories;

import com.autofix.msrepairVehicles.entities.VehicleTempEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTempRepository extends JpaRepository<VehicleTempEntity, String> {

}
