package com.autofix.msrepairVehicles.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vehiclesEntity {
    private long id;

    private String patente;

    private String marca;

    private String modelo;

    private String tipo;

    private String anio_fabricacion;

    private String tipo_motor;

    private String numero_asientos;

    private int numero_reparaciones;

    private int kilometraje;
}
