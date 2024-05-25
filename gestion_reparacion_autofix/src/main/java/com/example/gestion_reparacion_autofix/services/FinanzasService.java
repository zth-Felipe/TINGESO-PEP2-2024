package com.example.gestion_reparacion_autofix.services;

import com.example.gestion_reparacion_autofix.entities.BonoEntity;
import com.example.gestion_reparacion_autofix.entities.BonoPatenteEntity;
import com.example.gestion_reparacion_autofix.entities.HistorialEntity;
import com.example.gestion_reparacion_autofix.entities.RegistroEntity;
import com.example.gestion_reparacion_autofix.repositories.BonoPatenteRepository;
import com.example.gestion_reparacion_autofix.repositories.BonoRepository;
import com.example.gestion_reparacion_autofix.repositories.HistorialRepository;
import com.example.gestion_reparacion_autofix.repositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class FinanzasService {
    @Autowired
    RegistroRepository registroRepository;
    @Autowired
    HistorialRepository historialRepository;
    @Autowired
    BonoRepository bonoRepository;
    @Autowired
    BonoPatenteRepository bonoPatenteRepository;

    public int sumaTotal(String patente, LocalDate fechaIngreso,LocalDate fechaCliente){
        List<HistorialEntity> historial = historialRepository.findAllByPatenteAndFechaIngresoAndFechaCliente(patente, fechaIngreso, fechaCliente);
        int total = 0;
        for (int i=0 ; i < historial.size() ; i++){
            HistorialEntity re = historial.get(i);
            total = total + re.getMontoTotal();
        }
        return total;
    }

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
        if (bonoPatenteRepository.findByPatenteAndMesAndAno(patente, mes,ano) != null){
            return true;
        }
        return false;
    }

    // Descuento Bono


    public int descuentoBono(String patente, int mes, int ano) {
        String marca = registroRepository.findByPatente(patente).getMarca().toUpperCase();
        if (bonoRepository.findByMarcaAndMesAndAno(marca, mes, ano) != null){
            BonoEntity bono = bonoRepository.findByMarcaAndMesAndAno(marca, mes, ano);
            if (existeCantidad(bono.getMarca(), mes, ano) && (!inBonoPatenteRepo(patente, mes, ano))) {
                actualizarCantidad(bono.getCantidad() - 1, bono.getMarca(), mes, ano);
                BonoPatenteEntity bonoPatente = new BonoPatenteEntity(patente, mes, ano);
                bonoPatenteRepository.save(bonoPatente);
                return bono.getMonto();
            }else{
                return 0;
            }
        }
        return 0;
    }


    // Descuento Cantidad de Reparaciones
    public double cantReparaciones(String patente, LocalDate fechaingreso, LocalDate fechacliente) {

        List<HistorialEntity> listaGeneral = historialRepository.findAllByPatenteAndFechaIngresoAndFechaCliente(patente,fechaingreso,fechacliente);

        ArrayList<HistorialEntity> listaAuto = new ArrayList<HistorialEntity>();
        for (int i = 0; i < listaGeneral.size(); i++) {
            if (DAYS.between((listaGeneral.get(i).getFechaIngreso()), LocalDate.now()) >= 365) {
                listaAuto.add(listaGeneral.get(i));
            }
        }

        int cantidad = listaAuto.size();
        double descuentoCR = 0;
        String tipo = registroRepository.findByPatente(patente).getTipoMotor().toUpperCase();

        if (tipo.equals("GASOLINA")){
            if ((1 <= cantidad) && (cantidad <= 2)){
                descuentoCR = 0.05;
            }
            if ((3 <= cantidad) && (cantidad <= 5)){
                descuentoCR = 0.10;
            }
            if ((6 <= cantidad) && (cantidad <= 9)){
                descuentoCR = 0.15;
            }
            if (10 <= cantidad){
                descuentoCR = 0.20;
            }
        }
        if (tipo.equals("DIESEL")){
            if ((1 <= cantidad) && (cantidad <= 2)){
                descuentoCR = 0.07;
            }
            if ((3 <= cantidad) && (cantidad <= 5)){
                descuentoCR = 0.12;
            }
            if ((6 <= cantidad) && (cantidad <= 9)){
                descuentoCR = 0.17;
            }
            if (10 <= cantidad){
                descuentoCR = 0.22;
            }
        }
        if (tipo.equals("HIBRIDO")){
            if ((1 <= cantidad) && (cantidad <= 2)){
                descuentoCR = 0.10;
            }
            if ((3 <= cantidad) && (cantidad <= 5)){
                descuentoCR = 0.15;
            }
            if ((6 <= cantidad) && (cantidad <= 9)){
                descuentoCR = 0.20;
            }
            if (10 <= cantidad){
                descuentoCR = 0.25;
            }
        }
        if (tipo.equals("ELECTRICO")){
            if ((1 <= cantidad) && (cantidad <= 2)){
                descuentoCR = 0.08;
            }
            if ((3 <= cantidad) && (cantidad <= 5)){
                descuentoCR = 0.13;
            }
            if ((6 <= cantidad) && (cantidad <= 9)){
                descuentoCR = 0.18;
            }
            if (10 <= cantidad){
                descuentoCR = 0.23;
            }
        }
        return descuentoCR;
    }

    // Descuento Día de Ingreso
    public double descuentoDia(LocalDate fechaingreso, LocalTime horaingreso){
        String dia = String.valueOf(fechaingreso.getDayOfWeek()).toUpperCase();
        int hora1 = 9;
        int hora2 = 12;
        int horaIng = Integer.valueOf(horaingreso.getHour());
        double descuento = 0;

        if ((dia.equals("LUNES") || dia.equals("MONDAY") || dia.equals("JUEVES") || dia.equals("THURSDAY"))
            && ((hora1 < horaIng) || (horaIng < hora2))){
            descuento = 0.10;
        }
        return descuento;
    }

    // Recargo kilometraje
    public double recargoKilometraje(int kilometraje, String tipo){
        if ((tipo.toUpperCase().equals("SEDAN")) || (tipo.toUpperCase().equals("HATCHBACK"))){
            if ((0<= kilometraje) && (kilometraje <= 5000)){
                return 0;
            }
            if ((5001 <= kilometraje) && (kilometraje <= 12000)){
                return 0.03;
            }
            if ((12001 <= kilometraje) && (kilometraje <= 25000)){
                return 0.07;
            }
            if ((25001 <= kilometraje) && (kilometraje <= 40000)){
                return 0.12;
            }
            if (40001 <= kilometraje){
                return 0.20;
            }
        }
        if ((tipo.toUpperCase().equals("SUV")) || (tipo.toUpperCase().equals("PICKUP")) || (tipo.toUpperCase().equals("FURGONETA"))){
            if ((0< kilometraje) && (kilometraje < 5000)){
                return 0;
            }
            if ((5001 < kilometraje) && (kilometraje < 12000)){
                return 0.05;
            }
            if ((12001 < kilometraje) && (kilometraje < 25000)){
                return 0.09;
            }
            if ((25001 < kilometraje) && (kilometraje < 40000)){
                return 0.12;
            }
            if (40001 < kilometraje){
                return 0.20;
            }
        }
        return 0;
    }

    // Recargo Antiguedad
    public double recargoAntiguedad(String tipo, int anoAntiguedad){

        if ((tipo.toUpperCase().equals("SEDAN")) || (tipo.toUpperCase().equals("HATCHBACK"))){
            if ((0 < anoAntiguedad) && (anoAntiguedad < 5)){
                return 0;
            }
            if ((6 < anoAntiguedad) && (anoAntiguedad < 10)){
                return 0.05;
            }
            if ((11 < anoAntiguedad) && (anoAntiguedad < 15)){
                return 0.09;
            }
            if (16 < anoAntiguedad){
                return 0.15;
            }
        }
        if ((tipo.toUpperCase().equals("SUV")) || (tipo.toUpperCase().equals("PICKUP")) || (tipo.toUpperCase().equals("FURGONETA"))){
            if ((0 < anoAntiguedad) && (anoAntiguedad < 5)){
                return 0;
            }
            if ((6 < anoAntiguedad) && (anoAntiguedad < 10)){
                return 0.07;
            }
            if ((11 < anoAntiguedad) && (anoAntiguedad < 15)){
                return 0.11;
            }
            if (16 < anoAntiguedad){
                return 0.20;
            }
        }
        return 0;
    }

    LocalDateTime myDate = LocalDateTime.now();

    // Recargo retraso
    public double recargoRetraso(LocalDate fechaSalida, LocalDate fechaCliente){
        int dias = (int) DAYS.between(fechaSalida, fechaCliente);
        if (dias > 0){
            return  0.05 * dias;
        }else {
            return 0;
        }
    }

    public List<Double> costoTotalReparacion(String patente, LocalDate fechaIngreso, LocalDate fechaSalida, LocalDate fechaCliente, int kilometraje){

        RegistroEntity vehiculo = registroRepository.findByPatente(patente);
        String tipoMotor = vehiculo.getTipoMotor().toUpperCase();
        String tipoVehiculo = vehiculo.getTipo().toUpperCase();
        double total = sumaTotal(patente, fechaIngreso, fechaCliente);
        int descuentoTotal = 0;
        List<HistorialEntity> historial = historialRepository.findAllByPatenteAndFechaIngresoAndFechaCliente(patente, fechaIngreso, fechaCliente);
        int conteo = historial.size();


        // ------------- descuentos -----------------
        // Descuento Cantidad de Reparaciones
        double descuentoCR = cantReparaciones(patente, fechaIngreso, fechaCliente)*total;

        // Descuento por Día de Reparación

        LocalTime horaIngreso = historial.get(0).getHoraIngreso();
        double descuentoD = descuentoDia(fechaIngreso,horaIngreso)*total;

        // Descuento Bono
        int mes = fechaIngreso.getMonthValue();
        int ano = fechaIngreso.getYear();
        double descuentoB = descuentoBono(patente, mes, ano);

        // -------------- Recargos -----------------
        // Recargo por kilometraje
        double recargoK = recargoKilometraje(kilometraje, tipoVehiculo);

        // Recargo por antiguedad
        int anoActual = LocalDate.now().getYear();
        int anoFabricacion = vehiculo.getAnoFabr();
        int anoAntiguedad = anoActual - anoFabricacion;

        double recargoA = recargoAntiguedad(tipoVehiculo, anoAntiguedad);

        // Recargo por Retraso
        double recargoR = recargoRetraso(fechaSalida, fechaCliente);

        ArrayList<Double> costos = new ArrayList<Double>();
        costos.add(total);
        costos.add(descuentoCR);
        costos.add(descuentoD);
        costos.add(descuentoB);
        costos.add(recargoK);
        costos.add(recargoA);
        costos.add(recargoR);
        return costos;
    }

}
