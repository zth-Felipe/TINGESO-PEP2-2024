package com.example.ms_reparaciones_vehiculos.controllers;

import com.example.ms_reparaciones_vehiculos.entities.HistorialEntity;
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

    @GetMapping("/datos-repositorios")
    public ResponseEntity<List<Object>> obtenerDatosRepositorios() {
        List<Object> datos = historialService.obtenerDatosRepositorios();
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/{patente}")
    public ResponseEntity<List<HistorialEntity>> listHistorialPatente(@PathVariable("patente") String patent){
        List<HistorialEntity> historial = historialService.getHistorialPatente(patent);
        return ResponseEntity.ok(historial);
    }


    @PostMapping("/")
    public ResponseEntity<HistorialEntity> saveRegistro(@RequestBody HistorialEntity historial){
        HistorialEntity nuevoHistorial = historialService.saveHistorial(historial);
        return ResponseEntity.ok(nuevoHistorial);
    }


}
