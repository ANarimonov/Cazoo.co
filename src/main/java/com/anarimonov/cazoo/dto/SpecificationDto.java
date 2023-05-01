package com.anarimonov.cazoo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecificationDto {
    private Long id;
    private Integer topSpeed;
    private Double acceleration;
    private Integer enginePower;
    private Integer length;
    private Integer valves;
    private Integer cylinders;
    private Double height;
    private Double width;
    private Integer wheelbase;
    private Integer fuelCapacity;
    private Integer weight;
    private Integer bootSpace;
    private Long carId;
}
