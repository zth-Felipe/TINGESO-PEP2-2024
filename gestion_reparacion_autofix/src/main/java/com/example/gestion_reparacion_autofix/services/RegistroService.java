package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.RegistroEntity;
import com.example.gestion_reparacion_autofix.repositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;

    public ArrayList<RegistroEntity> getRegistros(){
        return (ArrayList<RegistroEntity>) registroRepository.findAll();
    }
    public RegistroEntity saveRegistro(RegistroEntity registro){
        return registroRepository.save(registro);
    }
    public RegistroEntity getRegistro(String patente){
        return registroRepository.findByPatente(patente);
    }
}
