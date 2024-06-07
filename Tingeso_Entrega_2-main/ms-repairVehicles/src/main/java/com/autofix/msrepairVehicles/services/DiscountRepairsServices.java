package com.autofix.msrepairVehicles.services;

import com.autofix.msrepairVehicles.entities.DiscountRepairsEntity;
import com.autofix.msrepairVehicles.repositories.DiscountRepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountRepairsServices {
    @Autowired
    DiscountRepairsRepository discountRepairsRepository;

    public DiscountRepairsEntity findById(int idDiscount){
        return discountRepairsRepository.findById(idDiscount);
    }

    public DiscountRepairsEntity saveDiscountRepairs(DiscountRepairsEntity discountRepairsEntity){
        return discountRepairsRepository.save(discountRepairsEntity);
    }
}
