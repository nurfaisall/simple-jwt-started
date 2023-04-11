package com.faisal.jwtmedium.service;

import com.faisal.jwtmedium.dto.LoginCredentials;
import com.faisal.jwtmedium.entity.User;
import com.faisal.jwtmedium.repository.UserRepository;
import com.faisal.jwtmedium.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    public String register(User user){

        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());

        return token;
    }

    public String login(LoginCredentials loginCredentials){
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginCredentials.getEmail(),
                    loginCredentials.getPassword()
            );

            authenticationManager.authenticate(authToken);

            String token = jwtUtil.generateToken(loginCredentials.getEmail());
            return token;
        } catch (AuthenticationException e){
            throw new RuntimeException("invalid login credentials");
        }
    }
}
