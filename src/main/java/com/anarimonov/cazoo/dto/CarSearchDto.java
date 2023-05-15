package com.anarimonov.cazoo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
//    List<String> features;
}
