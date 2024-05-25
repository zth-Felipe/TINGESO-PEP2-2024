package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.ReparacionesTipoEntity;
import com.example.gestion_reparacion_autofix.repositories.ReparacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ReparacionesService {
    @Autowired
    ReparacionesRepository reparacionesRepository;
    public ArrayList<ReparacionesTipoEntity> getReparaciones(){
        return (ArrayList<ReparacionesTipoEntity>) reparacionesRepository.findAll();
    }
    public ReparacionesTipoEntity saveReparacion(ReparacionesTipoEntity reparacion){
        return reparacionesRepository.save(reparacion);
    }


}
