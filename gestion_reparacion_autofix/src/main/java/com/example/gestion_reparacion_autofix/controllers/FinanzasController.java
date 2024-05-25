package com.example.gestion_reparacion_autofix.controllers;

import com.example.gestion_reparacion_autofix.services.FinanzasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
@CrossOrigin("*")
public class FinanzasController {
    @Autowired
    FinanzasService finanzasService;

    @GetMapping("/{patente}/{fechaingreso}/{fechasalida}/{fechacliente}/{kilometraje}")
    public ResponseEntity<List<Double>> sumaTotal(@PathVariable("patente") String patente,
                                                        @PathVariable("fechaingreso") LocalDate fechaing,
                                                        @PathVariable("fechasalida") LocalDate fechasal,
                                                        @PathVariable("fechacliente") LocalDate fechacli,
                                                        @PathVariable("kilometraje") Integer kilo){
        List<Double> costos = finanzasService.costoTotalReparacion(patente,fechaing, fechasal,fechacli, kilo);
        return ResponseEntity.ok(costos);
    }
}
