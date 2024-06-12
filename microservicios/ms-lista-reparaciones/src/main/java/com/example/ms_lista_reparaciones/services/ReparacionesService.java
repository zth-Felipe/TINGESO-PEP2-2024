package com.example.ms_lista_reparaciones.services;

import com.example.ms_lista_reparaciones.entities.ReparacionesTipoEntity;
import com.example.ms_lista_reparaciones.repositories.ReparacionesRepository;
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
    public ReparacionesTipoEntity getReparacion(Integer id){
        return reparacionesRepository.findByTipo(id);
    }
    public ReparacionesTipoEntity saveReparacion(ReparacionesTipoEntity reparacion){
        return reparacionesRepository.save(reparacion);
    }


}
