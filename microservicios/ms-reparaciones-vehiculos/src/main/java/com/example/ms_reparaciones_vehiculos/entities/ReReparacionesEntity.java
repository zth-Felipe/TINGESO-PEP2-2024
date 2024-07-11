package com.example.ms_reparaciones_vehiculos.entities;

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
    private int sedanCash;
    private int hatchback;
    private int hatchbackCash;
    private int suv;
    private int suvCash;
    private int pickup;
    private int pickupCash;
    private int furgoneta;
    private int furgonetaCash;
    private int total;
}
