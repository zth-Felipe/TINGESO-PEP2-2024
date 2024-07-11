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
public class RtReparacionesEntity {
    private int tipoRepa;
    private int ano;
    private int mes;
    private int cantidadRepa;
    private int monto;
}