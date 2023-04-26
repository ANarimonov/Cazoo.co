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
@Entity(name = "users_infos")
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String birthday;
    private String region;
    private String city;
}
