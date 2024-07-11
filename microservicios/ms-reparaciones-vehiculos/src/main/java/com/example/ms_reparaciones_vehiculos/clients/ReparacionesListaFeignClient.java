package com.example.ms_reparaciones_vehiculos.clients;

import com.example.ms_reparaciones_vehiculos.models.ReparacionesEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "ms-lista-reparaciones", path = "/api/reparaciones")
public interface ReparacionesListaFeignClient {
    @GetMapping("/{id}")
    ReparacionesEntity getReparacion(@PathVariable Integer id);

    @GetMapping("/")
    List<ReparacionesEntity> getReparaciones();

    @GetMapping("/gasolina/{id}")
    Integer getPrecioGasolina(@PathVariable Integer id);
}
