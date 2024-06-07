package com.autofix.msrepairList.services;

import com.autofix.msrepairList.entities.RepairsEntity;
import com.autofix.msrepairList.repositories.RepairsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RepairsServices {

    @Autowired
    RepairsRepository repairsRepository;

    //Metodo para obtener todas las reparaciones
    public ArrayList<RepairsEntity> getRepairs(){
        return repairsRepository.findAll();
    }

    //Metodo para guardar una reparacion en la BD
    public RepairsEntity saveRepair(RepairsEntity repair){
        return repairsRepository.save(repair);
    }

    //Metodo para obtener los tipos de reparaciones
    public String[] getTypes(){
        return repairsRepository.getTypes();
    }

    //Metodo para obtener una reparacion por su id
    public RepairsEntity getRepairById(Long id) {
        return repairsRepository.findById(id).orElse(null);
    }

    //metodo para actualizar una reparacion
    public RepairsEntity updateRepair(RepairsEntity repair) {
        // Primero, intentamos encontrar la entidad existente en la base de datos.
        RepairsEntity existingRepair = repairsRepository.findById(repair.getId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la reparación con ID: " + repair.getId()));

        // Actualizamos los campos de la entidad existente con los valores proporcionados.
        existingRepair.setType(repair.getType());
        existingRepair.setDescription(repair.getDescription());
        existingRepair.setCost_gasoline(repair.getCost_gasoline());
        existingRepair.setCost_diesel(repair.getCost_diesel());
        existingRepair.setCost_hybrid(repair.getCost_hybrid());
        existingRepair.setCost_electric(repair.getCost_electric());

        // Guardamos la entidad actualizada. Como ya es una entidad gestionada, Hibernate realizará un UPDATE.
        return repairsRepository.save(existingRepair);
    }

}
