package com.faisal.jwtmedium.security;

import com.faisal.jwtmedium.entity.User;
import com.faisal.jwtmedium.repository.UserRepository;
import com.faisal.jwtmedium.service.FindEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FindEmailService service;

    private User findEmail(String email){
       User user = userRepository.findByEmail(email);
       log.info("email dari method find email"+user.getEmail());
       return user;

    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Boolean valid = "nur faisal".equals(email.replace("\"",""));
        log.info("-------->"+valid.toString()+ " = "+ email.replace("\"","")+" = "+ "nur faisal");
        User user1 = service.findEmail(email);
        log.info(user1.getEmail());
        if(user1 == null){
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        }

        // Return a User Details object using the fetched User information
        User user = user1;
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); // Sets the role of the found user
    }
    }

