package com.autofix.msrepairVehicles.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Vehicle_Temp")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleTempEntity {

    @Id
    private String patente;

    private String tipo;

    private String tipo_motor;
}
