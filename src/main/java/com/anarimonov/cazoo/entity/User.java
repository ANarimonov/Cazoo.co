package com.anarimonov.cazoo.entity;

import com.anarimonov.cazoo.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;

    public User(String phoneNumber, String password, Role role, UserInfo userInfo) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.userInfo = userInfo;
    }
}
