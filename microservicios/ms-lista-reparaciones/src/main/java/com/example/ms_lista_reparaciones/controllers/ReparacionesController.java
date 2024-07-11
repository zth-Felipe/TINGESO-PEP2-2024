package com.example.ms_lista_reparaciones.controllers;

import com.example.ms_lista_reparaciones.entities.ReparacionesTipoEntity;
import com.example.ms_lista_reparaciones.services.ReparacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionesController {
    @Autowired
    ReparacionesService reparacionesService;

    @GetMapping("/")
    public ResponseEntity<List<ReparacionesTipoEntity>> getReparaciones(){
        List<ReparacionesTipoEntity> listaReparaciones = reparacionesService.getReparaciones();
        return ResponseEntity.ok(listaReparaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReparacionesTipoEntity> getReparacion(@PathVariable  Integer id){
        ReparacionesTipoEntity reparacion = reparacionesService.getReparacion(id);
        return ResponseEntity.ok(reparacion);
    }

    @GetMapping("/gasolina/{id}")
    public ResponseEntity<Integer> getPrecioGasolina(@PathVariable Integer id){
        Integer monto = reparacionesService.getReparacion(id).getGasolina();
        return ResponseEntity.ok(monto);
    }
}
