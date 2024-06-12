package com.example.ms_reparaciones_vehiculos.clients;


import com.example.ms_reparaciones_vehiculos.configurations.FeignClientConfig;
import com.example.ms_reparaciones_vehiculos.models.VehiculosEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "ms-vehiculos", path = "/api/register", configuration = FeignClientConfig.class)
public interface VehiculosFeignClient {

    @GetMapping("/")
    List<VehiculosEntity> listVehiculos();

    @PostMapping("/")
    VehiculosEntity saveVehiculo(@RequestBody VehiculosEntity vehiculo);

    @GetMapping("/{patente}")
    VehiculosEntity findByPatente(@PathVariable String patente);

}
