package com.guilherme.adopted.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.guilherme.adopted.dtos.AuthDto;
import com.guilherme.adopted.interfaces.IAuthService;
import com.guilherme.adopted.interfaces.ITokenService;
import com.guilherme.adopted.models.User;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final ITokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager, ITokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public String login(AuthDto auth) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(auth.email(), auth.password())
        );

        User userAuthenticated = (User) authentication.getPrincipal();

        return tokenService.generatedToken(userAuthenticated.getEmail());
    }

}
