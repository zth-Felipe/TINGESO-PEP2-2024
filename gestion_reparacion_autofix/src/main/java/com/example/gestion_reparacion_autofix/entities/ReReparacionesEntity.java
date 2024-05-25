package com.example.gestion_reparacion_autofix.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="re_reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
//--------Reparaciones / Tipos de vehículos y monto total---------
public class ReReparacionesEntity {
    @Id
    private int tipoRepa;
    private String nombreReparacion;
    private int sedan;
    private int hatchback;
    private int suv;
    private int pickup;
    private int furgoneta;
    private int total;
}
