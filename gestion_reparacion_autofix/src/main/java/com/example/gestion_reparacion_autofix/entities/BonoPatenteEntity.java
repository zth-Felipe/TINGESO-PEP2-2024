package com.example.gestion_reparacion_autofix.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bono-patente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonoPatenteEntity {
    @Id
    private String patente;
    private int mes;
    private int ano;
}
