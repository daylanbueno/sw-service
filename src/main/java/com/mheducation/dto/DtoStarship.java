package com.mheducation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mheducation.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoStarship {
    private Integer id;
    private String name;
    private String model;
    @JsonProperty("starship_class")
    private String starshipClass;
    private String manufacturer;
    @JsonProperty("cost_in_credits")
    private String CostInCredits;
    private String length;
    private String crew;
    private String passengers;
    @JsonProperty("max_stmosphering_speed")
    private String maxAtmospheringSpeed;
    @JsonProperty("hyperdrive_rating")
    private String hyperdriveRating;
    private String mglt;
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
