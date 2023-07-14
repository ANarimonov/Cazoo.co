package com.anarimonov.cazoo.entity;

import com.anarimonov.cazoo.entity.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "cars")
public class Car{
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Maker maker;
    @ManyToOne
    private Model model;
    private Long price;
    private Integer manufacturedYear;
    private Double engine;
    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;
    private Integer mileage;
    @Enumerated(value = EnumType.STRING)
    private Gearbox gearbox;
    @Enumerated(value = EnumType.STRING)
    private Color color;
    @Enumerated(value = EnumType.STRING)
    private BodyType bodyType;
    @Enumerated(value = EnumType.STRING)
    private List<Feature> features;
    @OneToMany
    private List<Attachment> attachments;
    @CreationTimestamp
    private Timestamp registrationDate;

    public Car(Maker maker, Model model, Long price, Integer manufacturedYear, Double engine, FuelType fuelType, Integer mileage, Gearbox gearbox, Color color, BodyType bodyType, List<Feature> features, List<Attachment> attachments) {
        this.maker = maker;
        this.model = model;
        this.price = price;
        this.manufacturedYear = manufacturedYear;
        this.engine = engine;
        this.fuelType = fuelType;
        this.mileage = mileage;
        this.gearbox = gearbox;
        this.color = color;
        this.bodyType = bodyType;
        this.features = features;
        this.attachments = attachments;
    }

}
