package com.autofix.msrepairVehicles.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reparacion_temp")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReparacionTempEntity {

    @Id
    private long id;

    private String nombre;


    private float cost_gasoline;

    private float cost_diesel;

    private float cost_hybrid;

    private float cost_electric;

}
