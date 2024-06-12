package com.example.ms_reparaciones_vehiculos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bonos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonoEntity {
    @Id
    private String marca;
    private int cantidad;
    private int monto;
    private int mes;
    private int ano;
}
