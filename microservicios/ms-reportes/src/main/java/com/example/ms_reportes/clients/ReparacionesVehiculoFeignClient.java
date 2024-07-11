package com.example.ms_reportes.clients;


import com.example.ms_reportes.configurations.FeignClientConfig;
import com.example.ms_reportes.models.ReReparacionesEntity;
import com.example.ms_reportes.models.RtReparacionesEntity;
import com.example.ms_reportes.models.VariacionReparacionesDTOEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value= "ms-reparaciones-vehiculos", path = "/api/historial", configuration = FeignClientConfig.class)
public interface ReparacionesVehiculoFeignClient {

    @GetMapping("/Report1")
    List<ReReparacionesEntity> hacerReporte1();

    @GetMapping("/Report2/{year}/{month}")
    List<VariacionReparacionesDTOEntity> hacerReporte2(@PathVariable("year") int year, @PathVariable("month")int month);


}
