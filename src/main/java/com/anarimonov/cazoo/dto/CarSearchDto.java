package com.anarimonov.cazoo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarSearchDto {
    Long makerId;
    Long modelId;
    Long minPrice;
    Long maxPrice;
    List<String> fuelType;
    Integer minMileage;
    Integer maxMileage;
    List<String> gearbox;
    Double minEngine;
    Double maxEngine;
    Integer minManufacturedYear;
    Integer maxManufacturedYear;
    List<String> color;
    List<String> bodyType;
}
