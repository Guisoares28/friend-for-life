package com.guilherme.adopted.interfaces;

import com.guilherme.adopted.dtos.PetRequestDto;
import com.guilherme.adopted.dtos.PetResponseDto;
import com.guilherme.adopted.models.Pet;

public interface PetConverterInterface extends ConverterInterface<PetRequestDto, Pet, PetResponseDto> {

}
