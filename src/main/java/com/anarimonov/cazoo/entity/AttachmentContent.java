package com.anarimonov.cazoo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "attachment_contents")
public class AttachmentContent {

    public AttachmentContent(byte[] data, Attachment attachment) {
        this.data = data;
        this.attachment = attachment;
    }

    @Id
    @GeneratedValue
    private long id;
    private byte[] data;
    @OneToOne
    private Attachment attachment;
}
