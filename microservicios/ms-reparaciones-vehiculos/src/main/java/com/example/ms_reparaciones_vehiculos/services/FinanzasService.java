package com.example.ms_reparaciones_vehiculos.services;

import com.example.ms_reparaciones_vehiculos.clients.VehiculosFeignClient;
import com.example.ms_reparaciones_vehiculos.entities.*;
import com.example.ms_reparaciones_vehiculos.models.VehiculosEntity;
import com.example.ms_reparaciones_vehiculos.repositories.BonoPatenteRepository;
import com.example.ms_reparaciones_vehiculos.repositories.BonoRepository;
import com.example.ms_reparaciones_vehiculos.repositories.HistorialRepository;
import com.example.ms_reparaciones_vehiculos.repositories.VehiculoTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class FinanzasService {
    @Autowired
    private VehiculosFeignClient vehiculosFeignClient;
    @Autowired
    private VehiculoTempRepository registroRepository;
    @Autowired
    HistorialRepository historialRepository;
    @Autowired
    BonoRepository bonoRepository;
    @Autowired
    BonoPatenteRepository bonoPatenteRepository;


    private void crearTablaVehiculosTemporal(List<VehiculosEntity> vehiculos) {
        registroRepository.deleteAll();

        for (VehiculosEntity vehiculo : vehiculos) {
            VehiculoTempEntity vehiculoTemp = new VehiculoTempEntity();
            vehiculoTemp.setPatente(vehiculo.getPatente());
            vehiculoTemp.setTipo(vehiculo.getTipo());
            vehiculoTemp.setTipoMotor(vehiculo.getTipoMotor());
            registroRepository.save(vehiculoTemp);
        }
    }


    public double sumaTotal(List<HistorialDetalladoEntity> listaHD){
        double total = 0;
        for (int i=0 ; i < listaHD.size() ; i++){
            total = total + listaHD.get(i).getMontoReparacion();
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
    public double cantReparaciones(String patente, List<HistorialDetalladoEntity> listHD) {

        int cantidad = listHD.size();
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


    // Recargo retraso
    public double recargoRetraso(LocalDate fechaSalida, LocalDate fechaCliente){
        int dias = (int) DAYS.between(fechaSalida, fechaCliente);
        if (dias > 0){
            return  0.05 * dias;
        }else {
            return 0;
        }
    }

    public List<Double> costoTotalReparacion
            (String patente,
             List<HistorialDetalladoEntity> historialDetallado,
             LocalDate fechaIngreso,
             LocalDate fechaSalida,
             LocalDate fechaCliente,
             LocalTime horaIngreso,
             int kilometraje, int bono){

        VehiculoTempEntity vehiculo = registroRepository.findByPatente(patente);

        String tipoMotor = vehiculo.getTipoMotor().toUpperCase();

        String tipoVehiculo = vehiculo.getTipo().toUpperCase();

        double total = sumaTotal(historialDetallado);



        // ------------- descuentos -----------------
        // Descuento Cantidad de Reparaciones
        double descuentoCR = cantReparaciones(patente, historialDetallado)*total;

        // Descuento por Día de Reparación
        double descuentoD = descuentoDia(fechaIngreso,horaIngreso)*total;

        // Descuento Bono
        double descuentoB = 0;
        if (bono == 1){
            int mes = fechaIngreso.getMonthValue();
            int ano = fechaIngreso.getYear();
            descuentoB = descuentoBono(patente, mes, ano);
        }

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

        // Recargo IVA
        double descuentos = descuentoCR + descuentoD + descuentoB;
        double recargos = recargoK + recargoA + recargoR;
        double iva = ((total + recargos) - descuentos)*0.19;
        double costoTotal = total + recargos - descuentos + iva;

        ArrayList<Double> costos = new ArrayList<Double>();

        costos.add(total);
        costos.add(descuentos);
        costos.add(recargos);
        costos.add(iva);
        costos.add(costoTotal);
        return costos;
    }
}
