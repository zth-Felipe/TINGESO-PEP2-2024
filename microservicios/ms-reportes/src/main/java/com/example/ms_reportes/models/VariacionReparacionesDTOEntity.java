package com.example.ms_reportes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariacionReparacionesDTOEntity {
    private int tipoRepa;
    private int mesActual;
    private int cantidadRepaActual;
    private int montoActual;
    private int mesPrevio1;
    private int cantidadRepaPrevio1;
    private int montoPrevio1;
    private int mesPrevio2;
    private int cantidadRepaPrevio2;
    private int montoPrevio2;
    private int variacionCantidadMes1;
    private int variacionMontoMes1;
    private int variacionCantidadMes2;
    private int variacionMontoMes2;
}
