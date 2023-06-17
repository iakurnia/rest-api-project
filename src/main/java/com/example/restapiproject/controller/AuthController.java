package com.example.restapiproject.controller;

import com.example.restapiproject.service.TokenService;
import com.example.restapiproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> token(Authentication authentication){
        return ResponseEntity.ok(tokenService.generateToken(authentication));
    }

}
