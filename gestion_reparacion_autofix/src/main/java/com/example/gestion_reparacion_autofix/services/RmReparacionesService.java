package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.RmReparacionesEntity;
import com.example.gestion_reparacion_autofix.repositories.RmReparacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RmReparacionesService {
    @Autowired
    RmReparacionesRepository rmReparacionesRepository;

    public ArrayList<RmReparacionesEntity> getRmReparaciones(){
        return (ArrayList<RmReparacionesEntity>) rmReparacionesRepository.findAll();
    }
}
