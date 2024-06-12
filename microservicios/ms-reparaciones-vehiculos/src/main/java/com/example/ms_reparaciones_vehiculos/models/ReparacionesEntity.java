package com.example.ms_reparaciones_vehiculos.models;

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
