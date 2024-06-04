package com.example.ms_lista_repacaiones.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionesTipoEntity {
    @Id
    private int tipo;
    private int gasolina;
    private int diesel;
    private int hibrido;
    private int electrico;
}
