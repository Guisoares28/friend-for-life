package com.guilherme.adopted.interfaces;

import org.springframework.data.domain.Page;

import com.guilherme.adopted.dtos.PetRequestDto;
import com.guilherme.adopted.dtos.PetResponseDto;
import com.guilherme.adopted.dtos.PetUpdateDto;

public interface PetServiceInterface extends CrudService<PetRequestDto, PetResponseDto, PetUpdateDto, Long> {
    Page<PetResponseDto> getAll(int page, int size);
}
