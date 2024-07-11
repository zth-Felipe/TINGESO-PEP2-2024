package com.example.ms_vehiculos.controllers;

import com.example.ms_vehiculos.entities.RegistroEntity;
import com.example.ms_vehiculos.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
@CrossOrigin("*")
public class RegistroController {

    RegistroService registroService;

    public RegistroController(RegistroService registroService){this.registroService = registroService;}

    @GetMapping("/")
    public ResponseEntity<List<RegistroEntity>> listRegistros(){
        List<RegistroEntity> registros = registroService.getRegistros();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{patente}")
    public ResponseEntity<RegistroEntity> getRegistro(@PathVariable String patente){
        RegistroEntity registro = registroService.getRegistro(patente);
        return ResponseEntity.ok(registro);
    }

    @PostMapping("/")
    public ResponseEntity<RegistroEntity> saveRegistro(@RequestBody RegistroEntity registro){
        RegistroEntity nuevoRegistro = registroService.saveRegistro(registro);
        return ResponseEntity.ok(nuevoRegistro);
    }
}
