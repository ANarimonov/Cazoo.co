package com.anarimonov.cazoo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImperfectionDto {
    private Long id;
    private String description;
    private Long attachmentId;
    private Long carId;
}
