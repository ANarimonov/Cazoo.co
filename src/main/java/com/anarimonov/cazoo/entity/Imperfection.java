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
    private long id;
    @OneToOne
    private Attachment attachment;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
    @OneToOne
    private Car car;
}
