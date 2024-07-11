package com.example.ms_reportes.services;

import com.example.ms_reportes.clients.ReparacionesVehiculoFeignClient;
import com.example.ms_reportes.models.ReReparacionesEntity;
import com.example.ms_reportes.models.RtReparacionesEntity;
import com.example.ms_reportes.models.VariacionReparacionesDTOEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {
    @Autowired
    ReparacionesVehiculoFeignClient reparacionesVehiculoFeignClient;

    public List<ReReparacionesEntity> report1(){
        List<ReReparacionesEntity> lista = reparacionesVehiculoFeignClient.hacerReporte1();
        System.out.println(lista);
        return reparacionesVehiculoFeignClient.hacerReporte1();
    }

    public List<VariacionReparacionesDTOEntity> report2(int year, int month){
        return reparacionesVehiculoFeignClient.hacerReporte2(year,month);
    }

}
