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
    @Column(nullable = false)
    private Attachment attachment;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
    @OneToOne
    @Column(nullable = false)
    private Car car;
}
