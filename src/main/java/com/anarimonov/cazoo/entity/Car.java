package com.anarimonov.cazoo.entity;

import com.anarimonov.cazoo.entity.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;
    private String issuedYear;
    private int mileage;
    @Enumerated(value = EnumType.STRING)
    private Gearbox gearbox;
    private float engine;
    @Enumerated(value = EnumType.STRING)
    private Color color;
    @Enumerated(value = EnumType.STRING)
    private BodyType bodyType;
    @Enumerated(value = EnumType.STRING)
    private List<Feature> features;
    @OneToMany
    private List<Attachment> photos;
    @OneToMany
    private List<Imperfection> imperfections;
    @CreationTimestamp
    private Timestamp registrationDate;
    @OneToOne
    private Specification specification;
}
