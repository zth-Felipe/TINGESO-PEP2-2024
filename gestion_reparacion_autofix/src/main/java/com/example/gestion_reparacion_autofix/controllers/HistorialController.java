package com.example.gestion_reparacion_autofix.controllers;

import com.example.gestion_reparacion_autofix.entities.HistorialEntity;
import com.example.gestion_reparacion_autofix.entities.RegistroEntity;
import com.example.gestion_reparacion_autofix.services.HistorialService;
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


    @PostMapping("/")
    public ResponseEntity<HistorialEntity> saveRegistro(@RequestBody HistorialEntity historial){
        HistorialEntity nuevoHistorial = historialService.saveHistorial(historial);
        return ResponseEntity.ok(nuevoHistorial);
    }


}
