package com.faisal.jwtmedium.controller;


import com.faisal.jwtmedium.entity.User;
import com.faisal.jwtmedium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/hello")
    public ResponseEntity<String> getUserDetails(){
        String email = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().replace("\"","");

        return ResponseEntity.ok(email);
    }
}
