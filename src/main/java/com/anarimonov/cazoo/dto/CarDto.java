package com.anarimonov.cazoo.dto;

import com.anarimonov.cazoo.entity.Maker;
import com.anarimonov.cazoo.entity.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
    private Long id;
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
