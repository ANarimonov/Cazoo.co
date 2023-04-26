package com.anarimonov.cazoo.entity;

import com.anarimonov.cazoo.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String phoneNumber;
    private String password;
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;
}
