package com.guilherme.adopted.interfaces;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface ITokenService {

    String generatedToken();

    DecodedJWT verifyToken(String token);
}
