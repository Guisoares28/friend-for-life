package com.guilherme.adopted.controllers;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.adopted.dtos.AuthDto;
import com.guilherme.adopted.dtos.TokenDto;
import com.guilherme.adopted.interfaces.IAuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping()
    public ResponseEntity<TokenDto> authLogin(@RequestBody AuthDto authDto) {
        String token = this.authService.login(authDto);
        return ResponseEntity.status(200).body(new TokenDto(token, LocalDateTime.now()));
    }

}
