package com.autofix.msrepairVehicles.services;

import com.autofix.msrepairVehicles.entities.GenerateRepairsDetailsEntity;
import com.autofix.msrepairVehicles.repositories.GenerateRepairsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateRepairsDetailsService {

    @Autowired
    GenerateRepairsDetailsRepository generateRepairsDetailsRepository;

    public GenerateRepairsDetailsEntity findByReparacionId(int id_reparacion){
        return generateRepairsDetailsRepository.findByReparacion_id(id_reparacion);
    }
}

