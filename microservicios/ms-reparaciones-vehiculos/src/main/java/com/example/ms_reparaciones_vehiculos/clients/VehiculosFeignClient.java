package com.example.ms_reparaciones_vehiculos.clients;


import com.example.ms_reparaciones_vehiculos.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "ms-vehiculos",
        path = "/api/register",
        configuration = {FeignClientConfig.class})
public interface VehiculosFeignClient {
    @GetMapping("/")
    List<RegistroEntity>
}
