package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.BonoPatenteEntity;
import com.example.gestion_reparacion_autofix.repositories.BonoPatenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonoPatenteService {
    @Autowired
    BonoPatenteRepository bonoPatenteRepository;

    public boolean inBonoPatenteRepo(String patente, int mes, int ano) {
        BonoPatenteEntity vehiculo = new BonoPatenteEntity(patente,mes,ano);
        List<BonoPatenteEntity> lista_vehiculo = bonoPatenteRepository.findAllByPatente(patente);
        if (lista_vehiculo.contains(vehiculo)) {
            return true;
        }else {
            return false;
        }
    }

    public boolean agregarBonoPatente(String patente, int mes, int ano){
        BonoPatenteEntity bono = new BonoPatenteEntity(patente,mes,ano);
        bonoPatenteRepository.save(bono);
        return true;
    }

}
