package com.anarimonov.cazoo.dto;

import com.anarimonov.cazoo.entity.Maker;
import com.anarimonov.cazoo.entity.Model;
import lombok.Getter;

import java.util.List;

@Getter
public class CarDto {
    private Long makerId;
    private Long modelId;
    private Long price;
    private String fuelType;
    private Integer manufacturedYear;
    private Integer mileage;
    private String gearbox;
    private Double engine;
    private String color;
    private String bodyType;
    private List<String> features;
    private List<Long> photosIds;
    private Maker maker;
    private Model model;
}
