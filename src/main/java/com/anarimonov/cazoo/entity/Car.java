package com.anarimonov.cazoo.entity;

import com.anarimonov.cazoo.entity.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "cars")
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Maker maker;
    private String model;
    private Long price;
    private FuelType fuelType;
    private String issuedYear;
    private int mileage;
    private Gearbox gearbox;
    private float engine;
    private Color color;
    private BodyType bodyType;
    private List<Feature> features;
    @OneToMany
    private List<AttachmentContent> photos;
    @OneToMany
    private List<Imperfection> imperfections;
    private Timestamp registrationDate;
    private Specification specification;
}
