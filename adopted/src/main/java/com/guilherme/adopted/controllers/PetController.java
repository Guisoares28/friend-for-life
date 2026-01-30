package com.guilherme.adopted.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.adopted.dtos.PetRequestDto;
import com.guilherme.adopted.dtos.PetResponseDto;
import com.guilherme.adopted.services.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pet")
public class PetController {

    private PetService petService;

    public PetController(PetService petService){
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetResponseDto> create(@RequestBody @Valid PetRequestDto petRequestDto){
        PetResponseDto response = this.petService.create(petRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
