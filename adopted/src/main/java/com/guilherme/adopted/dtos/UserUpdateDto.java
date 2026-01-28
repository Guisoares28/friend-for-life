package com.guilherme.adopted.dtos;

import java.time.LocalDate;

public record UserUpdateDto(
    String name,
    LocalDate dateOfBirth,
    String telephone
) {

}
