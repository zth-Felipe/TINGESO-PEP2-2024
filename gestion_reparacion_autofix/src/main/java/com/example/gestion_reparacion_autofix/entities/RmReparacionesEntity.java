package com.example.gestion_reparacion_autofix.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rm_reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
//-----Reparaciones / Tipo Motor y monto total------
public class RmReparacionesEntity {
    @Id
    private int tipoRepa;
    private String nombreReparacion;
    private int gasolina;
    private int diesel;
    private int hibrido;
    private int electrico;
    private int total;
}
