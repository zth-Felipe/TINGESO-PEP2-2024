package com.example.ms_reparaciones_vehiculos.controllers;

import com.example.ms_reparaciones_vehiculos.entities.*;
import com.example.ms_reparaciones_vehiculos.services.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
@CrossOrigin("*")
public class HistorialController {
    @Autowired
    HistorialService historialService;

    @GetMapping("/")
    public ResponseEntity<List<HistorialEntity>> listHistorial(){
        List<HistorialEntity> historial = historialService.getHistorial();
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/{patente}")
    public ResponseEntity<List<HistorialEntity>> listHistorialPatente(@PathVariable("patente") String patent){
        List<HistorialEntity> historial = historialService.getHistorialPatente(patent);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/datos-repositorios")
    public ResponseEntity<List<Object>> obtenerDatosRepositorios() {
        List<Object> datos = historialService.obtenerDatosRepositorios();
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/Report1")
    public ResponseEntity<List<ReReparacionesEntity>> hacerReporte1(){
        List<ReReparacionesEntity> reporte = historialService.generarReporte1();
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/Reporte2/{year}/{month}")
    public ResponseEntity<List<VariacionReparacionesDTOEntity>> hacerReporte2(@PathVariable("year") int year,@PathVariable("month") int month){
        List<VariacionReparacionesDTOEntity> reporte = historialService.generarReporteComparativo(year, month);
        return ResponseEntity.ok(reporte);
    }

    @PostMapping("/{bono}")
    public ResponseEntity<HistorialEntity> saveHistorial(@RequestBody HistorialEntity historial,@PathVariable("bono") int bono){
        HistorialEntity nuevoHistorial = historialService.saveHistorial(historial, bono);
        return ResponseEntity.ok(nuevoHistorial);
    }

    @PostMapping("/detallado")
    public ResponseEntity<HistorialDetalladoEntity> saveHistorialDetallado(@RequestBody HistorialDetalladoEntity historialDetallado){
        HistorialDetalladoEntity historialD = historialService.saveHistorialDetallado(historialDetallado);
        return ResponseEntity.ok(historialD);
    }

    @GetMapping("/detallado")
    public ResponseEntity<List<HistorialDetalladoEntity>> listHistorialDetallado(){
        List<HistorialDetalladoEntity> historialD = historialService.getHistorialDetallado();
        return ResponseEntity.ok(historialD);
    }

    @GetMapping("/detallado/{patente}")
    public ResponseEntity<List<HistorialDetalladoEntity>> listHistorialPatenteDetallado(@PathVariable("patente") String patente){
        List<HistorialDetalladoEntity> historialD = historialService.getHistorialDetalladoPatente(patente);
        return ResponseEntity.ok(historialD);
    }


}
