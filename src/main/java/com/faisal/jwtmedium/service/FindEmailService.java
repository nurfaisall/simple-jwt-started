package com.faisal.jwtmedium.service;

import com.faisal.jwtmedium.entity.User;
import com.faisal.jwtmedium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindEmailService {

    @Autowired
    UserRepository userRepository;
    public User findEmail(String email){
        String manipulate = email.replace("\"","");
        return userRepository.findByEmail(manipulate);

    }

}
