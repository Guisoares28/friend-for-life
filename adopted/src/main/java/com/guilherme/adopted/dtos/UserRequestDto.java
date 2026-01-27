package com.guilherme.adopted.dtos;

import java.sql.Date;

public record UserRequestDto(
    String name,
    Date dateOfBirth,
    String email,
    String telephone,
    String password
) {

}
