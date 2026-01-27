package com.guilherme.adopted.dtos;

import java.sql.Date;
import java.util.UUID;

public record UserResponseDto(
    UUID id,
    String name,
    Date dateOfBirth,
    String email,
    String telephone,
    String password
) {

}