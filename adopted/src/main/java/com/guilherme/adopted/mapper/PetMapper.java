package com.guilherme.adopted.mapper;

import org.springframework.stereotype.Component;

import com.guilherme.adopted.dtos.PetRequestDto;
import com.guilherme.adopted.dtos.PetResponseDto;
import com.guilherme.adopted.interfaces.PetConverterInterface;
import com.guilherme.adopted.models.Pet;

@Component
public class PetMapper implements PetConverterInterface {

    @Override
    public Pet toEntity(PetRequestDto requestObject) {
        Pet pet = new Pet();

        pet.setAddress(requestObject.address());
        pet.setApproximateAge(requestObject.approximateAge());
        pet.setDescription(requestObject.description());
        pet.setEspecieEnum(requestObject.speciesEnum());
        pet.setName(requestObject.name());
        pet.setRace(requestObject.race());
        pet.setSizeEnum(requestObject.sizeEnum());
        pet.setWeight(requestObject.weight());
        pet.setmicrochip(requestObject.microchip());

        return pet;
    }

    @Override
    public PetResponseDto toResponse(Pet entityObject) {
        return new PetResponseDto(
            entityObject.getId(),
            entityObject.getName(),
            entityObject.getApproximateAge(),
            entityObject.getWeight(),
            entityObject.getmicrochip(),
            entityObject.getEspecieEnum(),
            entityObject.getSizeEnum(),
            entityObject.getRace(),
            entityObject.getAddress(),
            entityObject.getDescription()
        );
    }

}
