package com.autofix.msrepairVehicles.controllers;

import com.autofix.msrepairVehicles.services.DiscountRepairsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.autofix.msrepairVehicles.entities.DiscountRepairsEntity;
@RestController
@RequestMapping("/api/v1/repairs/discountrepairs")
public class DiscountRepairsController {
    @Autowired
    DiscountRepairsServices discountRepairsServices;

    @GetMapping("/{id}")
    public DiscountRepairsEntity findByIdDiscount(@PathVariable("id") int idDiscount) {
        return discountRepairsServices.findById(idDiscount);
    }
    @PostMapping("/")
    public DiscountRepairsEntity saveDiscountRepairs(@RequestBody DiscountRepairsEntity discountRepairsEntity){
        return discountRepairsServices.saveDiscountRepairs(discountRepairsEntity);
    }

}
