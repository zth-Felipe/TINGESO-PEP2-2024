package com.autofix.msrepairVehicles.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepairsEntity {
    private long id;

    private String type;

    private String description;

    private float cost_gasoline;

    private float cost_diesel;

    private float cost_hybrid;

    private float cost_electric;
}
