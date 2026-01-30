package com.guilherme.adopted.dtos;

import com.guilherme.adopted.enums.SizeEnum;
import com.guilherme.adopted.enums.SpeciesEnum;
import com.guilherme.adopted.interfaces.Response;

public record PetResponseDto(

    Long id,

    String name,

    Integer approximateAge,

    Float weight,

    String microchip,

    SpeciesEnum speciesEnum,

    SizeEnum sizeEnum,

    String race,

    String address,

    String description
) implements Response {

}
