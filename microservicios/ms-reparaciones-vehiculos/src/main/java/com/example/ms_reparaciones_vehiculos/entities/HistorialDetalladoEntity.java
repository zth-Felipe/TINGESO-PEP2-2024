package com.example.ms_reparaciones_vehiculos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "historial_detallado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialDetalladoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "id_historial")
    private long idHistorial;

    @Column(name = "patente")
    private String patente;

    @Column(name = "id_reparacion")
    private int idReparacion;

    @Column(name = "fecha_reparacion")
    private LocalDate fechaReparacion;

    @Column(name = "hora_reparacion")
    private LocalTime horaReparacion;

    @Column(name = "monto_reparacion")
    private double montoReparacion;
}