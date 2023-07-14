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
    private long id;
    private String fullName;
    private String birthday;
    private String region;
    private String city;

    public UserInfo(String fullName, String birthday, String region, String city) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.region = region;
        this.city = city;
    }
}
