package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.HistorialEntity;
import com.example.gestion_reparacion_autofix.entities.ReReparacionesEntity;
import com.example.gestion_reparacion_autofix.entities.RmReparacionesEntity;
import com.example.gestion_reparacion_autofix.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialService {
    @Autowired
    HistorialRepository historialRepository;
    @Autowired
    RegistroRepository registroRepository;
    @Autowired
    ReReparacionesRepository reReparacionesRepository;
    @Autowired
    ReparacionesRepository reparacionesRepository;
    @Autowired
    RmReparacionesRepository rmReparacionesRepository;

    public ArrayList<HistorialEntity> getHistorial(){
        return (ArrayList<HistorialEntity>) historialRepository.findAll();
    }

    public Integer obtenerMontoReparacion(HistorialEntity historial){
        int monto = 0;
        if (registroRepository.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("GASOLINA")){
            monto = reparacionesRepository.findByTipo(historial.getTipoReparacion()).getGasolina();
        }if (registroRepository.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("DIESEL")){
            monto = reparacionesRepository.findByTipo(historial.getTipoReparacion()).getDiesel();
        }if (registroRepository.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("HIBRIDO")){
            monto = reparacionesRepository.findByTipo(historial.getTipoReparacion()).getHibrido();
        }if (registroRepository.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("ELECTRICO")){
            monto = reparacionesRepository.findByTipo(historial.getTipoReparacion()).getElectrico();
        }
        return monto;
    }

    public HistorialEntity saveHistorial(HistorialEntity historial){


        // Tabla Reparaciones - Tipo Vehiculo
        String tipoVehiculo = registroRepository.findByPatente(historial.getPatente()).getTipo();
        if (tipoVehiculo.toUpperCase().equals("SEDAN")){
            ReReparacionesEntity actualizar = reReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setSedan(actualizar.getSedan() + 1);
            actualizar.setTotal(actualizar.getTotal() + monto);
            reReparacionesRepository.save(actualizar);
        }if (tipoVehiculo.toUpperCase().equals("HATCHBACK")){
            ReReparacionesEntity actualizar = reReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setHatchback(actualizar.getHatchback() + 1);
            actualizar.setTotal(actualizar.getTotal() + monto);
            reReparacionesRepository.save(actualizar);
        }if (tipoVehiculo.toUpperCase().equals("SUV")){
            ReReparacionesEntity actualizar = reReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setSuv(actualizar.getSuv() + 1);
            actualizar.setTotal(actualizar.getTotal() + monto);
            reReparacionesRepository.save(actualizar);
        }if (tipoVehiculo.toUpperCase().equals("PICKUP")){
            ReReparacionesEntity actualizar = reReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setPickup(actualizar.getPickup() + 1);
            actualizar.setTotal(actualizar.getTotal() + monto);
            reReparacionesRepository.save(actualizar);
        }if (tipoVehiculo.toUpperCase().equals("FURGONETA")){
            ReReparacionesEntity actualizar = reReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setFurgoneta(actualizar.getFurgoneta() + 1);
            actualizar.setTotal(actualizar.getTotal() + monto);
            reReparacionesRepository.save(actualizar);
        }

        // Tabla Reparaciones - Tipo de Motor
        String tipoMotor = registroRepository.findByPatente(historial.getPatente()).getTipoMotor();
        if (tipoMotor.toUpperCase().equals("GASOLINA")){
            RmReparacionesEntity actualizar = rmReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setTotal(actualizar.getTotal() + monto);
            actualizar.setGasolina(actualizar.getGasolina() + 1);
            rmReparacionesRepository.save(actualizar);
        }if (tipoMotor.toUpperCase().equals("DIESEL")){
            RmReparacionesEntity actualizar = rmReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setTotal(actualizar.getTotal() + monto);
            actualizar.setDiesel(actualizar.getDiesel() + 1);
            rmReparacionesRepository.save(actualizar);
        }if (tipoMotor.toUpperCase().equals("HIBRIDO")){
            RmReparacionesEntity actualizar = rmReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setTotal(actualizar.getTotal() + monto);
            actualizar.setHibrido(actualizar.getHibrido() + 1);
            rmReparacionesRepository.save(actualizar);
        }if (tipoMotor.toUpperCase().equals("ELECTRICO")){
            RmReparacionesEntity actualizar = rmReparacionesRepository.findByTipoRepa(historial.getTipoReparacion());
            int monto = obtenerMontoReparacion(historial);
            actualizar.setTotal(actualizar.getTotal() + monto);
            actualizar.setElectrico(actualizar.getElectrico() + 1);
            rmReparacionesRepository.save(actualizar);
        }

        int montoHistorial = obtenerMontoReparacion(historial);
        historial.setMontoTotal(montoHistorial);
        return historialRepository.save(historial);
    }

    public List<HistorialEntity> getHistorialPatente(String patente){
        return historialRepository.findAllByPatente(patente);
    }

}
