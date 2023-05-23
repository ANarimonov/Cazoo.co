package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(String username);
}
