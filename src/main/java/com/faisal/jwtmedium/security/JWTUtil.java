package com.faisal.jwtmedium.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String email){
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withClaim("ROLE", "USER")
                .withIssuedAt(new Date())
                .withIssuer("authentication project example")
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveString(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("authentication project example")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").toString();
    }

}
