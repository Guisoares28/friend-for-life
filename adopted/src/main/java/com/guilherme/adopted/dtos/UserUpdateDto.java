package com.guilherme.adopted.dtos;

import java.time.LocalDate;

import com.guilherme.adopted.interfaces.Update;

public record UserUpdateDto(
    String name,
    LocalDate dateOfBirth,
    String telephone
) implements Update {

}
