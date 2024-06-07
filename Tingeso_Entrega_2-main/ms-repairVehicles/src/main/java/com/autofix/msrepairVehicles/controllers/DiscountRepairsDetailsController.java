package com.autofix.msrepairVehicles.controllers;

import com.autofix.msrepairVehicles.services.GenerateRepairsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autofix.msrepairVehicles.entities.GenerateRepairsDetailsEntity;

@RestController
@RequestMapping("/api/v1/repairs/discountrepairsdetails")
public class DiscountRepairsDetailsController {

    @Autowired
    GenerateRepairsDetailsService generateRepairsDetailsService;

    @GetMapping("/{id}")
    public GenerateRepairsDetailsEntity findById_reparacion(@PathVariable("id") int id_reparacion) {
        return generateRepairsDetailsService.findByReparacionId(id_reparacion);
    }
}
