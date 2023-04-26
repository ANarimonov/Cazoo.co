package com.anarimonov.cazoo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private int topSpeed;
    private double acceleration;
    private int enginePower;
    private int length;
    private int valves;
    private int cylinders;
    private double height;
    private double width;
    private int wheelbase;
    private int fuelCapacity;
    private int weight;
    private int bootSpace;
}
