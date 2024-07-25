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
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private HistorialDetalladoRepository historialDetalladoRepository;

    @Autowired
    private VehiculosFeignClient vehiculosFeignClient;

    @Autowired
    private ReparacionesListaFeignClient reparacionesListaFeignClient;

    @Autowired
    private VehiculoTempRepository registroRepository;

    @Autowired
    private ReparacionesTempRepository reparacionesRepository;

    @Autowired
    private ReReparacionesRepository reReparacionesRepository;

    @Autowired
    private RmReparacionesRepository rmReparacionesRepository;

    @Autowired
    private RtReparacionesRepository rtReparacionesRepository;

    @Autowired
    private FinanzasService finanzasService;


    private void crearTablaReparacionesTemporal(List<ReparacionesEntity> reparacionesLista) {
        reparacionesRepository.deleteAll();

        for (ReparacionesEntity reparacion : reparacionesLista) {
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
        registroRepository.deleteAll();

        for (VehiculosEntity vehiculo : vehiculos) {
            VehiculoTempEntity vehiculoTemp = new VehiculoTempEntity();
            vehiculoTemp.setId(vehiculo.getId());
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

    public HistorialDetalladoEntity saveHistorialDetallado(HistorialDetalladoEntity historialD){
        System.out.println(historialD);
        crearTablaReparacionesTemporal(reparacionesListaFeignClient.getReparaciones());
        System.out.println(vehiculosFeignClient.listVehiculos());
        crearTablaVehiculosTemporal(vehiculosFeignClient.listVehiculos());

        int tipoReparacion = historialD.getIdReparacion();
        String tipoMotor = registroRepository.findByPatente(historialD.getPatente()).getTipoMotor();
        System.out.println(reparacionesRepository.findByTipo(tipoReparacion));
        double monto = 0;
        switch (tipoMotor.toUpperCase()) {
            case "GASOLINA":
                monto = reparacionesRepository.findByTipo(tipoReparacion).getGasolina();
                break;
            case "DIESEL":
                monto = reparacionesRepository.findByTipo(tipoReparacion).getDiesel();
                break;
            case "HIBRIDO":
                monto = reparacionesRepository.findByTipo(tipoReparacion).getHibrido();
                break;
            case "ELECTRICO":
                monto = reparacionesRepository.findByTipo(tipoReparacion).getElectrico();
                break;
        }

        historialD.setMontoReparacion(monto);

        String tipoVehiculo = registroRepository.findByPatente(historialD.getPatente()).getTipo().toUpperCase();
        actualizarReparacionesPorTipoVehiculo(historialD, tipoVehiculo);
        actualizarReparacionesPorTipoMotor(historialD, tipoMotor);

        return historialDetalladoRepository.save(historialD);
    }

    public ArrayList<HistorialDetalladoEntity> getHistorialDetallado() {
        return (ArrayList<HistorialDetalladoEntity>) historialDetalladoRepository.findAll();
    }

    public List<HistorialDetalladoEntity> getHistorialDetalladoPatente(String patente) {
        return historialDetalladoRepository.findAllByPatente(patente);
    }


    public ArrayList<HistorialEntity> getHistorial() {
        return (ArrayList<HistorialEntity>) historialRepository.findAll();
    }

    public List<HistorialEntity> getHistorialPatente(String patente) {
        return historialRepository.findAllByPatente(patente);
    }

    public Integer obtenerMontoReparacion(HistorialDetalladoEntity historialD) {
        crearTablaReparacionesTemporal(reparacionesListaFeignClient.getReparaciones());
        String tipoMotor = registroRepository.findByPatente(historialD.getPatente()).getTipoMotor().toUpperCase();
        int monto = 0;
        switch (tipoMotor) {
            case "GASOLINA":
                monto = reparacionesRepository.findByTipo(historialD.getIdReparacion()).getGasolina();
                break;
            case "DIESEL":
                monto = reparacionesRepository.findByTipo(historialD.getIdReparacion()).getDiesel();
                break;
            case "HIBRIDO":
                monto = reparacionesRepository.findByTipo(historialD.getIdReparacion()).getHibrido();
                break;
            case "ELECTRICO":
                monto = reparacionesRepository.findByTipo(historialD.getIdReparacion()).getElectrico();
                break;
        }
        return monto;
    }

    public double recorrerListaObtenerMonto(List<HistorialDetalladoEntity> lista){
        int largo = lista.size();
        double monto = 0;
        for (int i = 0; i < largo; i ++){
            monto = monto + lista.get(i).getMontoReparacion();
        }
        if (monto == 0){
            System.out.println("Error de ingreso de datos o no elementos en la lista obtenida.");
            return monto;
        }else {
            System.out.println("Operacion realizada con exito.");
            return monto;
        }
    }

    public HistorialEntity saveHistorial(HistorialEntity historial, int bono) {


        List<HistorialDetalladoEntity> listHistorialD = historialDetalladoRepository.findAllByPatenteAndFechaReparacionBeforeAndFechaReparacionAfter
                (historial.getPatente(),historial.getFechaSalida().plusDays(1), historial.getFechaIngreso().minusDays(1));

        // Monto Total
        double montoTotal = recorrerListaObtenerMonto(listHistorialD);

        // Descuento Cantidad Reparaciones
        double cantidadReparaciones = listHistorialD.size();

        // Desarrollo de costos
        List<Double> listaCostos = finanzasService.costoTotalReparacion(
                        historial.getPatente(), listHistorialD,
                        historial.getFechaIngreso(), historial.getFechaSalida(), historial.getFechaCliente(),
                        historial.getHoraIngreso(), registroRepository.findByPatente(historial.getPatente()).getKilometraje(), bono);

        // Ingresar Costos
        historial.setMontoTotal(listaCostos.get(0));
        historial.setMontoDescuento(listaCostos.get(1));
        historial.setMontoRecargo(listaCostos.get(2));
        historial.setMontoIva(listaCostos.get(3));
        historial.setCostoTotal(listaCostos.get(4));

        HistorialEntity savedHistorial = historialRepository.save(historial);

        for (HistorialDetalladoEntity h : listHistorialD){
            h.setIdHistorial(savedHistorial.getId());
            historialDetalladoRepository.save(h);
        }

        return savedHistorial;
    }

    private void actualizarReparacionesPorTipoVehiculo(HistorialDetalladoEntity historial, String tipoVehiculo) {
        ReReparacionesEntity actualizar = reReparacionesRepository.findByTipoRepa(historial.getIdReparacion());
        int mes = historial.getFechaReparacion().getMonthValue();
        int ano = historial.getFechaReparacion().getYear();
        RtReparacionesEntity actualizar2 = new RtReparacionesEntity();
        if (rtReparacionesRepository.findByAnoAndMes(ano, mes) != null){
            actualizar2 = rtReparacionesRepository.findByTipoRepaAndAnoAndMes(historial.getIdReparacion(), ano, mes);
        }else {
            actualizar2 = new RtReparacionesEntity(historial.getIdReparacion(), ano, mes, 0, 0);
        }

        int monto = obtenerMontoReparacion(historial);

        switch (tipoVehiculo) {
            case "SEDAN":
                actualizar.setSedan(actualizar.getSedan() + 1);
                actualizar.setSedanCash((int) (actualizar.getSedanCash() + historial.getMontoReparacion()));
                break;
            case "HATCHBACK":
                actualizar.setHatchback(actualizar.getHatchback() + 1);
                actualizar.setHatchbackCash((int) (actualizar.getHatchbackCash() + historial.getMontoReparacion()));
                break;
            case "SUV":
                actualizar.setSuv(actualizar.getSuv() + 1);
                actualizar.setSuvCash((int) (actualizar.getSuvCash() + historial.getMontoReparacion()));
                break;
            case "PICKUP":
                actualizar.setPickup(actualizar.getPickup() + 1);
                actualizar.setPickupCash((int) (actualizar.getPickupCash() + historial.getMontoReparacion()));
                break;
            case "FURGONETA":
                actualizar.setFurgoneta(actualizar.getFurgoneta() + 1);
                actualizar.setFurgonetaCash((int) (actualizar.getFurgonetaCash()+ historial.getMontoReparacion()));
                break;
        }
        actualizar2.setCantidadRepa(actualizar2.getCantidadRepa() + 1);
        actualizar2.setMonto((int) (actualizar2.getMonto() + historial.getMontoReparacion()));
        actualizar.setTotal(actualizar.getTotal() + monto);
        reReparacionesRepository.save(actualizar);
    }

    private void actualizarReparacionesPorTipoMotor(HistorialDetalladoEntity historial, String tipoMotor) {
        RmReparacionesEntity actualizar = rmReparacionesRepository.findByTipoRepa(historial.getIdReparacion());
        int monto = obtenerMontoReparacion(historial);

        switch (tipoMotor) {
            case "GASOLINA":
                actualizar.setGasolina(actualizar.getGasolina() + 1);
                break;
            case "DIESEL":
                actualizar.setDiesel(actualizar.getDiesel() + 1);
                break;
            case "HIBRIDO":
                actualizar.setHibrido(actualizar.getHibrido() + 1);
                break;
            case "ELECTRICO":
                actualizar.setElectrico(actualizar.getElectrico() + 1);
                break;
        }
        actualizar.setTotal(actualizar.getTotal() + monto);
        rmReparacionesRepository.save(actualizar);
    }


    public List<Object> obtenerDatosRepositorios() {
        List<Object> datos = new ArrayList<>();
        datos.addAll(vehiculosFeignClient.listVehiculos());
        datos.addAll(reReparacionesRepository.findAll());
        datos.addAll(reparacionesListaFeignClient.getReparaciones());
        datos.addAll(rmReparacionesRepository.findAll());
        return datos;
    }

    public List<ReReparacionesEntity> generarReporte1() {
        // Obtener lista de reparaciones
        System.out.println(vehiculosFeignClient);
        System.out.println(reReparacionesRepository);
        List<ReReparacionesEntity> reparacionesLista = reReparacionesRepository.findAll();
        return reparacionesLista;
    }

    public List<VariacionReparacionesDTOEntity> generarReporteComparativo(int year, int month) {
        List<RtReparacionesEntity> inf1 = rtReparacionesRepository.findAllByAnoAndMes(year, month);
        List<RtReparacionesEntity> inf2 = new ArrayList<>();
        List<RtReparacionesEntity> inf3 = new ArrayList<>();

        if ((month - 1) > 0) {
            inf2 = rtReparacionesRepository.findAllByAnoAndMes(year, month - 1);
        } else {
            inf2 = rtReparacionesRepository.findAllByAnoAndMes(year - 1, 12);
        }

        if ((month - 2) > 0) {
            inf3 = rtReparacionesRepository.findAllByAnoAndMes(year, month - 2);
        } else {
            if ((month - 2) == 0) {
                inf3 = rtReparacionesRepository.findAllByAnoAndMes(year - 1, 12);
            } else {
                inf3 = rtReparacionesRepository.findAllByAnoAndMes(year - 1, 11);
            }
        }

        // Calcular variaciones y generar el reporte comparativo
        return calcularVariaciones(inf1, inf2, inf3, month);
    }
    private List<VariacionReparacionesDTOEntity> calcularVariaciones(List<RtReparacionesEntity> inf1, List<RtReparacionesEntity> inf2, List<RtReparacionesEntity> inf3, int month) {
        Map<Integer, RtReparacionesEntity> reparacionesMesActual = inf1.stream()
                .collect(Collectors.toMap(RtReparacionesEntity::getTipoRepa, r -> r));
        Map<Integer, RtReparacionesEntity> reparacionesMesPrevio1 = inf2.stream()
                .collect(Collectors.toMap(RtReparacionesEntity::getTipoRepa, r -> r));
        Map<Integer, RtReparacionesEntity> reparacionesMesPrevio2 = inf3.stream()
                .collect(Collectors.toMap(RtReparacionesEntity::getTipoRepa, r -> r));

        List<VariacionReparacionesDTOEntity> reporte = new ArrayList<>();

        for (Integer tipoRepa : reparacionesMesActual.keySet()) {
            RtReparacionesEntity actual = reparacionesMesActual.get(tipoRepa);
            RtReparacionesEntity previo1 = reparacionesMesPrevio1.get(tipoRepa);
            RtReparacionesEntity previo2 = reparacionesMesPrevio2.get(tipoRepa);

            int cantidadPrevio1 = previo1 != null ? previo1.getCantidadRepa() : 0;
            int montoPrevio1 = previo1 != null ? previo1.getMonto() : 0;
            int cantidadPrevio2 = previo2 != null ? previo2.getCantidadRepa() : 0;
            int montoPrevio2 = previo2 != null ? previo2.getMonto() : 0;

            int variacionCantidadMes1 = actual.getCantidadRepa() - cantidadPrevio1;
            int variacionMontoMes1 = actual.getMonto() - montoPrevio1;
            int variacionCantidadMes2 = actual.getCantidadRepa() - cantidadPrevio2;
            int variacionMontoMes2 = actual.getMonto() - montoPrevio2;

            VariacionReparacionesDTOEntity variacion = new VariacionReparacionesDTOEntity(
                    tipoRepa,
                    actual.getMes(), actual.getCantidadRepa(), actual.getMonto(),
                    month - 1 > 0 ? month - 1 : 12, cantidadPrevio1, montoPrevio1,
                    month - 2 > 0 ? month - 2 : (month - 2 == 0 ? 12 : 11), cantidadPrevio2, montoPrevio2,
                    variacionCantidadMes1, variacionMontoMes1,
                    variacionCantidadMes2, variacionMontoMes2
            );

            reporte.add(variacion);
        }

        return reporte;
    }


}