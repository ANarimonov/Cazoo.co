package com.anarimonov.cazoo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "specifications")
public class Specification {
    @Id
    @GeneratedValue
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
    @OneToOne
    private Car car;
}
