package com.example.ms_reparaciones_vehiculos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reparacion_temp")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReparacionesTempEntity {
    @Id
    private int tipo;
    private int gasolina;
    private int diesel;
    private int hibrido;
    private int electrico;
}
