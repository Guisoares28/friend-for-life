package com.guilherme.adopted.dtos;

import java.sql.Date;

public record UserUpdateDto(
    String name,
    Date dateOfBirth,
    String telephone
) {

}
