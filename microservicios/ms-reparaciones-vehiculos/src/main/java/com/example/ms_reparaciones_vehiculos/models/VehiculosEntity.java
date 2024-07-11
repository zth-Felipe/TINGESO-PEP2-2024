package com.example.ms_reparaciones_vehiculos.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculosEntity {
    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private String tipo;
    private int anoFabr;
    private String tipoMotor;
    private int numAsientos;
    private String bono;
    private int kilometraje;
}
