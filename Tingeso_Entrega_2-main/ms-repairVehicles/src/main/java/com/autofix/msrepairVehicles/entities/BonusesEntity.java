package com.autofix.msrepairVehicles.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Bonuses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BonusesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    private String marca;

    private String disponibilidad;

    private float monto;
}
