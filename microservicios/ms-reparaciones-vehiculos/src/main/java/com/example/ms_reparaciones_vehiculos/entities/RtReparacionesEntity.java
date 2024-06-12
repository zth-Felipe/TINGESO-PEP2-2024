package com.example.ms_reparaciones_vehiculos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rt_reparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
//----- Tiempos Promedios / Marcas de vehículos ------
public class RtReparacionesEntity {
    @Id
    private int tipoRepa;

}
