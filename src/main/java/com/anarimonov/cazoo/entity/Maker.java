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
@Entity(name = "makers")
public class Maker {
    
    public Maker(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
