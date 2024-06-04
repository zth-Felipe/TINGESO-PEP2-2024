package com.example.ms_reparaciones_vehiculos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "historial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String patente;
    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;
    private int tipoReparacion;
    private int montoTotal;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    private LocalDate fechaCliente;
    private LocalTime horaCliente;
}
