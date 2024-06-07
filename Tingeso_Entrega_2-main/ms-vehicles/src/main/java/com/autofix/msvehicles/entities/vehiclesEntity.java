package com.autofix.msvehicles.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Vehiculos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class vehiclesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(unique = true, nullable = false)
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
