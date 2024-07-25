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

    public ReparacionesTipoEntity modificarReparacion(ReparacionesTipoEntity nuevaRepa){
        ReparacionesTipoEntity reparacion = reparacionesRepository.findByTipo(nuevaRepa.getTipo());
        reparacion.setGasolina(nuevaRepa.getGasolina());
        reparacion.setElectrico(nuevaRepa.getElectrico());
        reparacion.setDiesel(nuevaRepa.getDiesel());
        reparacion.setHibrido(nuevaRepa.getHibrido());
        reparacion.setNombreRepa(nuevaRepa.getNombreRepa());
        return reparacionesRepository.save(reparacion);
    }

}
