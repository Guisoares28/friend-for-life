package com.guilherme.adopted.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.guilherme.adopted.interfaces.Response;

public record UserResponseDto(
    UUID id,
    String name,
    LocalDate dateOfBirth,
    String email,
    String telephone,
    String password
) implements Response {

}