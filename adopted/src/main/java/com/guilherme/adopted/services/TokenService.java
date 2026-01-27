package com.guilherme.adopted.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.guilherme.adopted.interfaces.ITokenService;

@Service
public class TokenService implements ITokenService {

    private Algorithm algorithm;

    public TokenService(Algorithm algorithm, @Value("${jwt.secret}") String secretKey) {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    @Override
    public String generatedToken() {
        String token = "";
        try{
            token = JWT.create()
            .withIssuer("adopted")
            .sign(this.algorithm);
        }catch(JWTCreationException exception){
            //tratar
        }   
        return token;
    }

    @Override
    public DecodedJWT verifyToken(String token) {
        DecodedJWT decodedJWT = null;
        try{
            JWTVerifier jwtVerifier = JWT.require(this.algorithm)
                .withIssuer("adopted")
                .build();

            decodedJWT = jwtVerifier.verify(token);
        }catch(JWTVerificationException exception){
            //tratar
        }
        return decodedJWT;
    }





}
