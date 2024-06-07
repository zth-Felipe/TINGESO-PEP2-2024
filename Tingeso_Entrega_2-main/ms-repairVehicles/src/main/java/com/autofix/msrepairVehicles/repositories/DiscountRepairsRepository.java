package com.autofix.msrepairVehicles.repositories;

import com.autofix.msrepairVehicles.entities.DiscountRepairsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepairsRepository extends JpaRepository<DiscountRepairsEntity, Long> {
    DiscountRepairsEntity findById(int idDiscount);
}
