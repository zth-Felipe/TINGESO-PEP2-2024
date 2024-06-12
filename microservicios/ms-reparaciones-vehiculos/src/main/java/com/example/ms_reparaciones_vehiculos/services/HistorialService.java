package com.example.ms_reparaciones_vehiculos.services;

import com.example.ms_reparaciones_vehiculos.clients.ReparacionesListaFeignClient;
import com.example.ms_reparaciones_vehiculos.clients.VehiculosFeignClient;
import com.example.ms_reparaciones_vehiculos.entities.*;
import com.example.ms_reparaciones_vehiculos.models.ReparacionesEntity;
import com.example.ms_reparaciones_vehiculos.models.VehiculosEntity;
import com.example.ms_reparaciones_vehiculos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialService {
    @Autowired
    HistorialRepository historialRepository;

    @Autowired
    VehiculosFeignClient vehiculosFeignClient;

    @Autowired
    ReparacionesListaFeignClient reparacionesListaFeignClient;

    @Autowired
    VehiculoTempRepository registroRepository;

    @Autowired
    ReparacionesTempRepository reparacionesRepository;

    @Autowired
    ReReparacionesRepository reReparacionesRepository;

    @Autowired
    RmReparacionesRepository rmReparacionesRepository;

    public ArrayList<HistorialEntity> getHistorial(){
        return (ArrayList<HistorialEntity>) historialRepository.findAll();
    }

    public Integer obtenerMontoReparacion(HistorialEntity historial){
        int monto = 0;
        if (vehiculosFeignClient.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("GASOLINA")){
            monto = reparacionesListaFeignClient.getReparacion(historial.getTipoReparacion()).getGasolina();
        }if (vehiculosFeignClient.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("DIESEL")){
            monto = reparacionesListaFeignClient.getReparacion(historial.getTipoReparacion()).getDiesel();
        }if (vehiculosFeignClient.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("HIBRIDO")){
            monto = reparacionesListaFeignClient.getReparacion(historial.getTipoReparacion()).getHibrido();
        }if (vehiculosFeignClient.findByPatente(historial.getPatente()).getTipoMotor().toUpperCase().equals("ELECTRICO")){
            monto = reparacionesListaFeignClient.getReparacion(historial.getTipoReparacion()).getElectrico();
        }
        return monto;
    }

    public HistorialEntity saveHistorial(HistorialEntity historial){


        // Tabla Reparaciones - Tipo Vehiculo
        String tipoVehiculo = vehiculosFeignClient.findByPatente(historial.getPatente()).getTipo();
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
        String tipoMotor = vehiculosFeignClient.findByPatente(historial.getPatente()).getTipoMotor();
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

    public List<Object> obtenerDatosRepositorios() {
        List<Object> datos = new ArrayList<>();
        datos.addAll(vehiculosFeignClient.listVehiculos());
        datos.addAll(reReparacionesRepository.findAll());
        datos.addAll(reparacionesListaFeignClient.getReparaciones());
        datos.addAll(rmReparacionesRepository.findAll());
        return datos;
    }

    private void crearTablaReparacionesTemporal(List<ReparacionesEntity> reparacionesLista) {
        // Borrar la tabla temporal si existe
        reparacionesRepository.deleteAll();

        // Crear la tabla temporal
        for (ReparacionesEntity reparacion : reparacionesLista){
            ReparacionesTempEntity reparacionTemp = new ReparacionesTempEntity();
            reparacionTemp.setTipo(reparacion.getTipo());
            reparacionTemp.setDiesel(reparacion.getDiesel());
            reparacionTemp.setElectrico(reparacion.getElectrico());
            reparacionTemp.setGasolina(reparacion.getGasolina());
            reparacionTemp.setHibrido(reparacion.getHibrido());
            reparacionesRepository.save(reparacionTemp);
        }


    }

    private void crearTablaVehiculosTemporal(List<VehiculosEntity> vehiculos) {
        // Borrar la tabla temporal si existe
        registroRepository.deleteAll();

        // Crear la tabla temporal
        for (VehiculosEntity vehiculo : vehiculos){
            VehiculoTempEntity vehiculoTemp = new VehiculoTempEntity();
            vehiculoTemp.setPatente(vehiculo.getPatente());
            vehiculoTemp.setMarca(vehiculo.getMarca());
            vehiculoTemp.setModelo(vehiculo.getModelo());
            vehiculoTemp.setTipo(vehiculo.getTipo());
            vehiculoTemp.setAnoFabr(vehiculo.getAnoFabr());
            vehiculoTemp.setTipoMotor(vehiculo.getTipoMotor());
            vehiculoTemp.setNumAsientos(vehiculo.getNumAsientos());
            vehiculoTemp.setBono(vehiculo.getBono());
            vehiculoTemp.setKilometraje(vehiculo.getKilometraje());
            registroRepository.save(vehiculoTemp);
        }
    }

}
