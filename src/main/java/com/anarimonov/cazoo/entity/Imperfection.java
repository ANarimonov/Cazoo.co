package com.anarimonov.cazoo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "imperfections")
public class Imperfection {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Attachment photos;
    @Column(columnDefinition = "text")
    private String description;
}
