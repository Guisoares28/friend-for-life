package com.guilherme.adopted.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
    @NotBlank
    @NotEmpty
    @NotNull
    String name,

    @NotNull
    LocalDate dateOfBirth,

    @Email
    @NotBlank
    @NotNull
    @NotEmpty
    String email,

    @Size(max = 11)
    String telephone,

    @NotBlank
    @NotEmpty
    @NotNull
    String password
) {

}
