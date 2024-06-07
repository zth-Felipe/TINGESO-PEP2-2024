package com.autofix.msvehicles.controllers;

import com.autofix.msvehicles.entities.vehiclesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.autofix.msvehicles.services.vehiclesService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class vehiclesController {

    @Autowired
    vehiclesService vehiclesService;

    @PostMapping("/")
    public ResponseEntity<vehiclesEntity> saveVehicle(@RequestBody vehiclesEntity vehicle) {
        vehiclesEntity vehicleNew = vehiclesService.saveVehicle(vehicle);
        return ResponseEntity.ok(vehicleNew);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<vehiclesEntity>> listVehicles() {
        Iterable<vehiclesEntity> vehicles = vehiclesService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{patente}")
    public ResponseEntity<vehiclesEntity> getVehiclesByPatente(@PathVariable String patente){
        vehiclesEntity vehicle = vehiclesService.getVehiclesByPatente(patente);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable long id){
        vehiclesService.deleteVehicle(id);
        return ResponseEntity.ok("Vehiculo eliminado");
    }

    @GetMapping("/patentes")
    public ResponseEntity<String[]> findAllPatente(){
        String[] patentes = vehiclesService.findAllPatente();
        return ResponseEntity.ok(patentes);
    }
}
