package com.example.servicesvehiculos.services;

import com.example.servicesvehiculos.entities.RegistroEntity;
import com.example.servicesvehiculos.repositories.RegistroRepository;
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
