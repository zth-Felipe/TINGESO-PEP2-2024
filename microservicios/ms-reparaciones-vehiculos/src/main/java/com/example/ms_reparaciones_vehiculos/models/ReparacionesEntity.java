package com.example.ms_reparaciones_vehiculos.models;

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
public class ReparacionesEntity {
    private int tipo;
    private int gasolina;
    private int diesel;
    private int hibrido;
    private int electrico;
}
