package com.faisal.jwtmedium.controller;

import com.faisal.jwtmedium.dto.LoginCredentials;
import com.faisal.jwtmedium.entity.User;
import com.faisal.jwtmedium.repository.UserRepository;
import com.faisal.jwtmedium.service.AuthService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentials loginCredentials){
       String token = authService.login(loginCredentials);
       return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user){
        String token = authService.register(user);
        HashMap<String,String > response = new HashMap<>();
        response.put("key", token);


       return ResponseEntity.ok(response);
    }

    @GetMapping("/test/{email}")
    public boolean test(@PathVariable("email")String email){
        User user1 = userRepository.findByEmail(email);

        return user1 == null;
    }

}
