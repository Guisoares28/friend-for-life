package com.guilherme.adopted.dtos;

import com.guilherme.adopted.enums.SizeEnum;
import com.guilherme.adopted.enums.SpeciesEnum;
import com.guilherme.adopted.interfaces.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PetRequestDto(

    @NotBlank
    @NotEmpty
    @NotNull
    String name,

    @NotNull
    Integer approximateAge,

    Float weight,

    String microchip,

    @NotNull
    SpeciesEnum speciesEnum,

    @NotNull
    SizeEnum sizeEnum,

    @NotNull
    String race,

    @NotBlank
    @NotEmpty
    @NotNull
    String address,

    @NotNull
    String description

) implements Request {

}
