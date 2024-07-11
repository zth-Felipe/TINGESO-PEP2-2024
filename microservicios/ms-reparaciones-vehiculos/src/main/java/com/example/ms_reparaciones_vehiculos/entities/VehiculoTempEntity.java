package com.example.ms_reparaciones_vehiculos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehiculo_temp")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehiculoTempEntity {
    @Id
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
