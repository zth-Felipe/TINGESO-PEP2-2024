package com.example.ms_lista_repacaiones.services;

import com.example.ms_lista_repacaiones.entities.ReparacionesTipoEntity;
import com.example.ms_lista_repacaiones.repositories.ReparacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
