package com.autofix.msrepairVehicles.services;

import com.autofix.msrepairVehicles.clients.RepairListFeignClient;
import com.autofix.msrepairVehicles.clients.VehiclesFeignClient;
import com.autofix.msrepairVehicles.entities.*;
import com.autofix.msrepairVehicles.models.RepairsEntity;
import com.autofix.msrepairVehicles.models.vehiclesEntity;
import com.autofix.msrepairVehicles.repositories.*;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GenerateRepairsServices {

    @Autowired
    VehiclesFeignClient vehiclesFeignClient;

    @Autowired
    RepairListFeignClient repairListFeignClient;

    @Autowired
    GenerateRepairsRepository generateRepairsRepository;

    @Autowired
    SurchargeMileageRepository surchargeMileageRepository;

    @Autowired
    SurchargeSeniorityRepository surchargeSeniorityRepository;

    @Autowired
    BonusesRepository bonusesRepository;

    @Autowired
    GenerateRepairsDetailsService generateRepairsDetailsService;
    @Autowired
    private GenerateRepairsDetailsRepository generateRepairsDetailsRepository;

    @Autowired
    ReparacionesTempRepository reparacionTempRepository;

    @Autowired
    VehicleTempRepository VehicleTempRepository;


    //Metodo para obtener todas las reparaciones generadas
    public ArrayList<GenerateRepairsEntity> getGenerateRepairs(){
        return generateRepairsRepository.findAll();
    }



    public double calcularMontoReparaciones(vehiclesEntity vehicle, List<Long> idsReparaciones) {
        double montoReparaciones = 0.0;
        for (Long id : idsReparaciones) {
            RepairsEntity repair = repairListFeignClient.getRepairById(id);
            System.out.println("Reparacion: " + repair.getType());
            if (repair != null) {
                switch (vehicle.getTipo_motor()) {
                    case "Diesel":
                        montoReparaciones += repair.getCost_diesel();
                        break;
                    case "Gasolina":
                        montoReparaciones += repair.getCost_gasoline();
                        break;
                    case "Híbrido":
                        montoReparaciones += repair.getCost_hybrid();
                        break;
                    case "Electrico":
                        montoReparaciones += repair.getCost_electric();
                        break;
                }
            }
        }
        return montoReparaciones;
    }



    public double calcularDescuentoPorHistorial(String tipoMotor, int numeroReparaciones) {
        double descuento = 0.0;
        if (numeroReparaciones > 0) {
            if (numeroReparaciones < 3) {
                descuento = (tipoMotor.equals("Diesel")) ? 0.07 : (tipoMotor.equals("Gasolina")) ? 0.05 : (tipoMotor.equals("Híbrido")) ? 0.10 : 0.08;
            } else if (numeroReparaciones < 6) {
                descuento = (tipoMotor.equals("Diesel")) ? 0.12 : (tipoMotor.equals("Gasolina")) ? 0.10 : (tipoMotor.equals("Híbrido")) ? 0.15 : 0.13;
            } else if (numeroReparaciones < 10) {
                descuento = (tipoMotor.equals("Diesel")) ? 0.17 : (tipoMotor.equals("Gasolina")) ? 0.15 : (tipoMotor.equals("Híbrido")) ? 0.20 : 0.18;
            } else {
                descuento = (tipoMotor.equals("Diesel")) ? 0.22 : (tipoMotor.equals("Gasolina")) ? 0.20 : (tipoMotor.equals("Híbrido")) ? 0.25 : 0.23;
            }
        }
        return descuento;
    }

    public double calcularDescuentoPorHora(LocalDateTime fechaIngreso, LocalTime horaIngreso) {
        if ((fechaIngreso.getDayOfWeek() == DayOfWeek.MONDAY || fechaIngreso.getDayOfWeek() == DayOfWeek.THURSDAY) &&
                horaIngreso.isAfter(LocalTime.of(9, 0)) && horaIngreso.isBefore(LocalTime.of(12, 0))) {
            return 0.05;
        }
        return 0.0;
    }

    public double calculoRecargoKilometraje(vehiclesEntity vehicle){
        SurchargeMileageEntity surchargeMileage = surchargeMileageRepository.findByKilometraje(vehicle.getKilometraje());

        if(vehicle.getTipo().equals("Sedan")){
            return surchargeMileage.getSedan();
        }else if(vehicle.getTipo().equals("Hatchback")){
            return surchargeMileage.getHatchback();
        }else if(vehicle.getTipo().equals("SUV")){
            return surchargeMileage.getSuv();
        }else if(vehicle.getTipo().equals("Pickup")){
            return surchargeMileage.getPickup();
        }else if(vehicle.getTipo().equals("Furgoneta")){
            return surchargeMileage.getFurgoneta();
        }else{
            return 0;
        }

    }

    public double calculoRecargoAntiguedad(vehiclesEntity vehicle){
        LocalDate fechaActual = LocalDate.now();
        int añoActual = fechaActual.getYear();
        int añoFabricacion = Integer.parseInt(vehicle.getAnio_fabricacion()); // Convierte el año de fabricación de String a int.
        int antiguedad = añoActual - añoFabricacion;

        System.out.println("Antiguedad: " + antiguedad);
        SurchargeSeniorityEntity surchargeSeniority = surchargeSeniorityRepository.findByAntiguedad(antiguedad);

        if(vehicle.getTipo().equals("Sedan")){
            return surchargeSeniority.getSedan();
        }else if(vehicle.getTipo().equals("Hatchback")){
            return surchargeSeniority.getHatchback();
        }else if(vehicle.getTipo().equals("SUV")){
            return surchargeSeniority.getSuv();
        }else if(vehicle.getTipo().equals("Pickup")){
            return surchargeSeniority.getPickup();
        }else if(vehicle.getTipo().equals("Furgoneta")){
            return surchargeSeniority.getFurgoneta();
        }else{
            return 0;
        }
    }

    public double obtenerDescuentoPorBono(vehiclesEntity vehicle){

        String marca_vehiculo = vehicle.getMarca();
        BonusesEntity bono = bonusesRepository.findByMarca(marca_vehiculo);

        try{
            if (bono != null) {
                if (Integer.parseInt(bono.getDisponibilidad()) != 0) {
                    return bono.getMonto();
                }else {
                    return 0;
                }
            }else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }

    public double obtenerRecargoPorRetraso(LocalDateTime fechaSalidaReparacion, LocalDateTime fechaEntregaCliente){
        long DiasRetraso = ChronoUnit.DAYS.between(fechaSalidaReparacion, fechaEntregaCliente);
        return DiasRetraso * 0.05;
    }

    public double calcularIVA(double subtotal) {
        return subtotal * 0.19;
    }

    public GenerateRepairsEntity getGenerateRepairsById(int idReparacion){
        return generateRepairsRepository.findById(idReparacion);
    }

    public Map<String, Object> saveGenerateRepairs(GenerateRepairsEntity generateRepairs, boolean uso_bono){
        // Se obtiene el vehículo
        vehiclesEntity vehicle = vehiclesFeignClient.getVehiclesByPatente(generateRepairs.getPatente_vehiculo());

        System.out.println("Vehiculo: " + vehicle.getPatente() + " " + vehicle.getMarca() + " " + vehicle.getModelo() + " " + vehicle.getAnio_fabricacion() + " " + vehicle.getTipo_motor() + " " + vehicle.getKilometraje() + " " + vehicle.getTipo() + " " + vehicle.getNumero_reparaciones());
        //Obtener los Ids de reparacion y calcular el montos de reparaciones

        List<Long> ids_reparaciones = Arrays.stream(generateRepairs.getTipo_reparacion().split(","))
                .map(Long::parseLong)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Ids de reparaciones: " + ids_reparaciones);

        double MontoReparaciones = calcularMontoReparaciones(vehicle, ids_reparaciones);

        System.out.println("Monto de reparaciones: " + MontoReparaciones);

        int CantidadReparaciones = generateRepairsRepository.getCountReparacionesByPatenteUnaño(generateRepairs.getPatente_vehiculo());

        System.out.println("Cantidad de reparaciones en el ultimo año: " + CantidadReparaciones);

        double Descuento_Historial_Reparaciones = calcularDescuentoPorHistorial(vehicle.getTipo_motor(), CantidadReparaciones) * MontoReparaciones;
        double Descuento_Fecha_Hora_Ingreso = calcularDescuentoPorHora(generateRepairs.getFecha_ingreso_taller(), generateRepairs.getHora_ingreso_taller()) * MontoReparaciones;
        double recargo_kilometraje = calculoRecargoKilometraje(vehicle) * MontoReparaciones;
        double recargo_antiguedad = calculoRecargoAntiguedad(vehicle) * MontoReparaciones;
        double RecargoDiasRetraso = obtenerRecargoPorRetraso(generateRepairs.getFecha_salida_reparacion(), generateRepairs.getFecha_entrega_cliente()) * MontoReparaciones;

        // Inicializar el monto de descuentos y recargos
        double totalDescuentos = Descuento_Historial_Reparaciones + Descuento_Fecha_Hora_Ingreso;
        double totalRecargos = recargo_kilometraje + recargo_antiguedad + RecargoDiasRetraso;

        // Verificar si se aplica descuento por bono
        double descuento_bono = 0;
        if (uso_bono) {
            // Verificar si el vehículo tiene bono disponible
            if (bonusesRepository.findByMarca(vehicle.getMarca()) != null) {
                if (Integer.parseInt(bonusesRepository.findByMarca(vehicle.getMarca()).getDisponibilidad()) > 0) {
                    descuento_bono = obtenerDescuentoPorBono(vehicle);
                    //Restamos 1 a la cantidad de bonos disponibles
                    BonusesEntity bono = bonusesRepository.findByMarca(vehicle.getMarca());
                    bono.setDisponibilidad(String.valueOf(Integer.parseInt(bono.getDisponibilidad()) - 1));
                    bonusesRepository.save(bono);
                }
            }
        }

        // Calcular el IVA
        double subtotal = MontoReparaciones + totalRecargos - totalDescuentos - descuento_bono;
        double iva = calcularIVA(subtotal);

        // Calcular el monto total
        double monto = subtotal + iva;

        //Crear la entidad de reparacion generada
        GenerateRepairsEntity Repairs = new GenerateRepairsEntity();

        //Asignar los valores a la entidad
        Repairs.setMonto_total_reparacion((float) monto);
        Repairs.setFecha_ingreso_taller(generateRepairs.getFecha_ingreso_taller());
        Repairs.setHora_ingreso_taller(generateRepairs.getHora_ingreso_taller());
        Repairs.setTipo_reparacion(ids_reparaciones.stream().map(String::valueOf).collect(Collectors.joining(",")));
        Repairs.setFecha_salida_reparacion(generateRepairs.getFecha_salida_reparacion());
        Repairs.setHora_salida_reparacion(generateRepairs.getHora_salida_reparacion());
        Repairs.setFecha_entrega_cliente(generateRepairs.getFecha_entrega_cliente());
        Repairs.setHora_entrega_cliente(generateRepairs.getHora_entrega_cliente());
        Repairs.setPatente_vehiculo(generateRepairs.getPatente_vehiculo());

        //Guardamos la entidad en la BD y obtenemos la llave primaria generada

        Long key = generateRepairsRepository.save(Repairs).getId();

        System.out.println("Id de la reparacion generada: " + key);

        //Aumentamos la cantidad de reparaciones del vehiculo
        vehicle.setNumero_reparaciones(vehicle.getNumero_reparaciones() + 1);
        vehiclesFeignClient.saveVehicle(vehicle);

        //Guardamos los detalles de la reparacion generada

        GenerateRepairsDetailsEntity details = new GenerateRepairsDetailsEntity();
        details.setReparacion_id(key);
        details.setTotal_descuento((float) totalDescuentos);
        details.setTotal_recargos((float) totalRecargos);
        details.setDescuento_bono((float) descuento_bono);
        details.setIva((float) iva);
        details.setMonto_reparaciones((float) MontoReparaciones);
        details.setTotal((float) monto);

        generateRepairsDetailsRepository.save(details);

        Map<String, Object> response = new HashMap<>();
        response.put("generateRepair", Repairs);
        response.put("totalDescuentos", totalDescuentos);
        response.put("totalRecargos", totalRecargos);
        response.put("descuentoBono", descuento_bono);
        response.put("iva", iva);
        response.put("montoReparaciones", MontoReparaciones);

        return response;

    }


    public Iterable<vehiclesEntity> listVehicles() {
        return vehiclesFeignClient.listVehicles();
    }



    public List<RepairsEntity> listRepairs(){
        return repairListFeignClient.listRepairs();
    }

    public List<Object[]> generarReporte() {
        // Obtener lista de reparaciones
        List<RepairsEntity> reparacionesLista = repairListFeignClient.listRepairs();
        Iterable<vehiclesEntity> vehiculos = vehiclesFeignClient.listVehicles();

        crearTablaReparacionesTemporal(reparacionesLista);
        crearTablaVehiculosTemporal(vehiculos);

        return generateRepairsRepository.GenerateReport1();

    }

    private void crearTablaReparacionesTemporal(List<RepairsEntity> reparacionesLista) {
        // Borrar la tabla temporal si existe
        reparacionTempRepository.deleteAll();

        // Crear la tabla temporal
        for (RepairsEntity reparacion : reparacionesLista){
            ReparacionTempEntity reparacionTemp = new ReparacionTempEntity();
            reparacionTemp.setId(reparacion.getId());
            reparacionTemp.setNombre(reparacion.getType());
            reparacionTemp.setCost_diesel(reparacion.getCost_diesel());
            reparacionTemp.setCost_electric(reparacion.getCost_electric());
            reparacionTemp.setCost_gasoline(reparacion.getCost_gasoline());
            reparacionTemp.setCost_hybrid(reparacion.getCost_hybrid());
            reparacionTemp.setCost_electric(reparacion.getCost_electric());
            reparacionTempRepository.save(reparacionTemp);
        }


    }

    private void crearTablaVehiculosTemporal(Iterable<vehiclesEntity> vehiculos) {
        // Borrar la tabla temporal si existe
        VehicleTempRepository.deleteAll();

        for (vehiclesEntity vehiculo : vehiculos){
            VehicleTempEntity vehiculoTemp = new VehicleTempEntity();
            vehiculoTemp.setPatente(vehiculo.getPatente());
            vehiculoTemp.setTipo(vehiculo.getTipo());
            vehiculoTemp.setTipo_motor(vehiculo.getTipo_motor());
            VehicleTempRepository.save(vehiculoTemp);
        }
    }

    public List<Object[]> GenerarReporte2(int year, int month) {
        return generateRepairsRepository.GenerateReport2(year, month, month - 1, month + 1);
    }
}
