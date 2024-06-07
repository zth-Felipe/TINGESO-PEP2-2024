package com.autofix.msrepairVehicles.clients;


import com.autofix.msrepairVehicles.configurations.FeignClientConfig;

import com.autofix.msrepairVehicles.models.vehiclesEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-vehicles", path = "/api/v1/vehicles", configuration = FeignClientConfig.class)
public interface VehiclesFeignClient {

    @PostMapping("/")
    vehiclesEntity saveVehicle(@RequestBody vehiclesEntity vehicle);

    @GetMapping("/")
    Iterable<vehiclesEntity> listVehicles();

    @GetMapping("/{patente}")
    vehiclesEntity getVehiclesByPatente(@PathVariable String patente);

    @DeleteMapping("/{id}")
    String deleteVehicle(@PathVariable long id);

    @GetMapping("/patentes")
    String[] findAllPatente();
}
