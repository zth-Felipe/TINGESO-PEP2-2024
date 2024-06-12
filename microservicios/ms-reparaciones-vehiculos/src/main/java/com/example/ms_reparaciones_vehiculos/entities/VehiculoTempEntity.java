package com.example.ms_reparaciones_vehiculos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Vehiculo_Temp")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehiculoTempEntity {
    @Id
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
