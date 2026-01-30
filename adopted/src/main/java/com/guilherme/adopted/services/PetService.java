package com.guilherme.adopted.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guilherme.adopted.Utils;
import com.guilherme.adopted.dtos.PetRequestDto;
import com.guilherme.adopted.dtos.PetResponseDto;
import com.guilherme.adopted.dtos.PetUpdateDto;
import com.guilherme.adopted.interfaces.PetConverterInterface;
import com.guilherme.adopted.interfaces.PetServiceInterface;
import com.guilherme.adopted.models.Pet;
import com.guilherme.adopted.models.User;
import com.guilherme.adopted.repositories.PetRepository;

@Service
public class PetService implements PetServiceInterface {

    private PetRepository petRepository;

    private PetConverterInterface petConverter;

    public PetService(PetRepository petRepository, PetConverterInterface petConverter) {
        this.petRepository = petRepository;
        this.petConverter = petConverter;

    }

    @Override
    public PetResponseDto create(PetRequestDto requestObject) {
        User user = Utils.getUserAuthenticate();
        Pet pet = this.petConverter.toEntity(requestObject);
        pet.setUser(user);
        this.petRepository.save(pet);
        return this.petConverter.toResponse(pet);
    }

    @Override
    public PetResponseDto get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<PetResponseDto> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public PetResponseDto update(PetUpdateDto updateObject, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public PetResponseDto delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
