package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.BonoEntity;
import com.example.gestion_reparacion_autofix.entities.BonoPatenteEntity;
import com.example.gestion_reparacion_autofix.repositories.BonoPatenteRepository;
import com.example.gestion_reparacion_autofix.repositories.BonoRepository;
import com.example.gestion_reparacion_autofix.repositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BonoService {
    @Autowired
    BonoRepository bonoRepository;

    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    BonoPatenteRepository bonoPatenteRepository;


    public BonoEntity saveBono(BonoEntity bono){
        return bonoRepository.save(bono);
    }
    public boolean deleteBono(BonoEntity bono) throws Exception {
        try{
            bonoRepository.delete(bono);
            return true;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean existeCantidad(String marca, int mes, int ano){
        if (bonoRepository.findByMarcaAndMesAndAno(marca,mes,ano).getCantidad() > 0){
            return true;
        }else{
            return false;
        }
    }

    public void actualizarCantidad(int cantidad, String marca, int mes, int ano){
        if (existeCantidad(marca, mes, ano)){
            BonoEntity bonoAntiguo = bonoRepository.findByMarca(marca);
            bonoAntiguo.setCantidad(cantidad);
        }
    }


    public boolean inBonoPatenteRepo(String patente, int mes, int ano) {
        BonoPatenteEntity vehiculo = new BonoPatenteEntity(patente,mes,ano);
        List<BonoPatenteEntity> lista_vehiculo = bonoPatenteRepository.findAllByPatente(patente);
        return lista_vehiculo.contains(vehiculo);
    }
    public int DescuentoBono(String patente, int mes, int ano) {
        BonoEntity bono = bonoRepository.findByMarcaAndMesAndAno(registroRepository.findByPatente(patente).getMarca(), mes, ano);
        if (existeCantidad(bono.getMarca(), mes, ano) && inBonoPatenteRepo(patente, mes, ano)) {
            actualizarCantidad(bono.getCantidad() - 1, bono.getMarca(), mes, ano);
            BonoPatenteEntity bonoPatente = new BonoPatenteEntity(patente, mes, ano);
            bonoPatenteRepository.save(bonoPatente);
            return bono.getMonto();
        } else {
            return 0;
        }
    }


}
