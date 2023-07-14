package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
