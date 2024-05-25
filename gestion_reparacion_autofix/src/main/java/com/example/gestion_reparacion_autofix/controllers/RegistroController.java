package com.example.gestion_reparacion_autofix.controllers;

import com.example.gestion_reparacion_autofix.entities.RegistroEntity;
import com.example.gestion_reparacion_autofix.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
@CrossOrigin("*")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @GetMapping("/")
    public ResponseEntity<List<RegistroEntity>> listRegistros(){
        List<RegistroEntity> registros = registroService.getRegistros();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{patente}")
    public ResponseEntity<RegistroEntity> getRegistro(@RequestBody String patente){
        RegistroEntity registro = registroService.getRegistro(patente);
        return ResponseEntity.ok(registro);
    }

    @PostMapping("/")
    public ResponseEntity<RegistroEntity> saveRegistro(@RequestBody RegistroEntity registro){
        RegistroEntity nuevoRegistro = registroService.saveRegistro(registro);
        return ResponseEntity.ok(nuevoRegistro);
    }
}
