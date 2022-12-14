package com.mheducation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mheducation.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoVehicle {
    private Integer id;
    private String name;
    private String model;
    @JsonProperty("vehicle_class")
    private String vehicleClass;
    private String manufacturer;
    private String length;
    @JsonProperty("cost_in_credits")
    private String costInCredits;
    private String crew;
    private String passengers;
    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;
    @JsonProperty("cargo_capacity")
    private String cargoCapacity;
    private String consumables;
    private List<String> films;
    private List<String> pilots;
    private String url;
    private String created;
    private String edited;
    private int count;

    public Integer getId() {
        return Utils.extractDigitsFromString(this.url);
    }
}
