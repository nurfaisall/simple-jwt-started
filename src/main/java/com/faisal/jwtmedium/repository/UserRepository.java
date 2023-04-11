package com.faisal.jwtmedium.repository;

import com.faisal.jwtmedium.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);


    User findByEmail(String email);
}
