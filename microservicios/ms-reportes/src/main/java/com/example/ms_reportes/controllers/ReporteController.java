package com.example.ms_reportes.controllers;

import com.example.ms_reportes.models.ReReparacionesEntity;
import com.example.ms_reportes.models.RtReparacionesEntity;
import com.example.ms_reportes.models.VariacionReparacionesDTOEntity;
import com.example.ms_reportes.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Reporte")
@CrossOrigin("*")
public class ReporteController {
    @Autowired
    ReporteService reporteService;

    @GetMapping("/report1")
    public ResponseEntity<List<ReReparacionesEntity>> reporte1(){
        List<ReReparacionesEntity> reporte = reporteService.report1();
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/report2/{year}/{month}")
    public ResponseEntity<List<VariacionReparacionesDTOEntity>> reporte2(@PathVariable("year") int year, @PathVariable("month") int month){
        List<VariacionReparacionesDTOEntity> reporte = reporteService.report2(year, month);
        return ResponseEntity.ok(reporte);
    }

}
