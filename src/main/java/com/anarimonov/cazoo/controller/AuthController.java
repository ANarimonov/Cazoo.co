package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.UserDto;
import com.anarimonov.cazoo.entity.User;
import com.anarimonov.cazoo.payload.JwtUtil;
import com.anarimonov.cazoo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody User user) {
        UserDetails userDetails = userService.loadUserByUsername(user.getPhoneNumber());
        if (passwordEncoder.matches(user.getPassword(),userDetails.getPassword())) {
            String token = JwtUtil.generateToken(user.getPhoneNumber());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body("Phone number or password incorrect");
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

}

