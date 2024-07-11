package com.example.ms_reportes.models;

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
public class ReReparacionesEntity {
    private int tipoRepa;
    private String nombreReparacion;
    private int sedan;
    private int sedan_cash;
    private int hatchback;
    private int hatchback_cash;
    private int suv;
    private int suv_cash;
    private int pickup;
    private int pickup_cash;
    private int furgoneta;
    private int furgoneta_cash;
    private int total;
}
