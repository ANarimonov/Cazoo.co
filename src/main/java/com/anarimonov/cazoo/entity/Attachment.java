package com.anarimonov.cazoo.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachments")
public class Attachment {
    public Attachment(String name, Long size, String contentType) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
    }

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private long size;
    private String contentType;
}
