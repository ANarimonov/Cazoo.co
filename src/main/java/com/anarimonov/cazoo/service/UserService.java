package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.entity.User;
import com.anarimonov.cazoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserService implements UserDetailsService {
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
}
