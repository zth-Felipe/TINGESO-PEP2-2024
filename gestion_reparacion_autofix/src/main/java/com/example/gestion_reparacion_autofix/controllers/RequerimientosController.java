package com.example.gestion_reparacion_autofix.controllers;

import com.example.gestion_reparacion_autofix.entities.ReReparacionesEntity;
import com.example.gestion_reparacion_autofix.entities.RmReparacionesEntity;
import com.example.gestion_reparacion_autofix.services.ReReparacionesService;
import com.example.gestion_reparacion_autofix.services.RmReparacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/requeriments")
@CrossOrigin("*")
public class RequerimientosController {
    @Autowired
    ReReparacionesService reReparacionesService;
    @Autowired
    RmReparacionesService rmReparacionesService;


    @GetMapping("/r2/")
    public ResponseEntity<ArrayList<ReReparacionesEntity>> listReReparaciones(){
        ArrayList<ReReparacionesEntity> registros = reReparacionesService.getReReparaciones();
        registros.sort(Comparator.comparing(ReReparacionesEntity::getTotal).reversed());
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/r4/")
    public ResponseEntity<ArrayList<RmReparacionesEntity>> listRmReparaciones(){
        ArrayList<RmReparacionesEntity> registros = rmReparacionesService.getRmReparaciones();
        registros.sort(Comparator.comparing(RmReparacionesEntity::getTotal).reversed());
        return ResponseEntity.ok(registros);
    }

}
