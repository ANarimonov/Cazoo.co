package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.AdminDto;
import com.anarimonov.cazoo.dto.UserDto;
import com.anarimonov.cazoo.entity.User;
import com.anarimonov.cazoo.entity.UserInfo;
import com.anarimonov.cazoo.entity.enums.Role;
import com.anarimonov.cazoo.repository.UserInfoRepository;
import com.anarimonov.cazoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            List<SimpleGrantedAuthority> strings = new ArrayList<>();
            strings.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(), strings);
        }
        throw new UsernameNotFoundException("User not found");
    }

    public HttpEntity<?> register(UserDto userDto) {
        try {
            UserInfo userInfo = userInfoRepository.save(new UserInfo(userDto.getFullName(), userDto.getBirthday(), userDto.getRegion(), userDto.getCity()));
            userRepository.save(new User(userDto.getPhoneNumber(), userDto.getPassword(), Role.ROLE_USER, userInfo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User successfully registered");
    }
}
