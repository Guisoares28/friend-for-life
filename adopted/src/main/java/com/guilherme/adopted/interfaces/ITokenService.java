package com.guilherme.adopted.interfaces;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface ITokenService {

    String generatedToken(String email);

    DecodedJWT verifyToken(String token);

    String getEmail(String token);
}