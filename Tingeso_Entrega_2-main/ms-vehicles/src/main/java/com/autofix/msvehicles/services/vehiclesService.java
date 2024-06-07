package com.autofix.msvehicles.services;

import com.autofix.msvehicles.entities.vehiclesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autofix.msvehicles.repositories.vehiclesRepository;

@Service
public class vehiclesService {

    @Autowired
    vehiclesRepository vehiclesRepository;

    //metodo para guardar
    public vehiclesEntity saveVehicle(vehiclesEntity vehicle){
        return vehiclesRepository.save(vehicle);
    }

    //metodo para obtener todos los vehiculos
    public Iterable<vehiclesEntity> getVehicles(){
        return vehiclesRepository.findAll();
    }

    //metodo para obtener un vehiculo por patente
    public vehiclesEntity getVehiclesByPatente(String patente){
        return vehiclesRepository.findByPatente(patente);
    }

    //metodo para eliminar un vehiculo
    public void deleteVehicle(long id){
        vehiclesRepository.deleteById(id);
    }

    //metodo para obtener todas las patentes
    public String[] findAllPatente(){
        return vehiclesRepository.findAllPatente();
    }
}
