package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
