package com.anarimonov.cazoo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "models")
public class Model {
    
    public Model(String name, Maker maker) {
        this.name = name;
        this.maker = maker;
    }
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    private Maker maker;
}
