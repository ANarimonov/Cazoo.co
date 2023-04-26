package com.anarimonov.cazoo.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachments")
public class Attachment {
    public Attachment(String name, Long size, String contentType, byte[] data) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.data = data;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long size;
    private String contentType;
    private byte[] data;
}
