package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.ReReparacionesEntity;
import com.example.gestion_reparacion_autofix.repositories.ReReparacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReReparacionesService {
    @Autowired
    ReReparacionesRepository reReparacionesRepository;

    public ArrayList<ReReparacionesEntity> getReReparaciones(){
        return (ArrayList<ReReparacionesEntity>) reReparacionesRepository.findAll();
    }
}
