package com.guilherme.adopted.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDto> get(@PathVariable Long id){
        PetResponseDto petResponse = this.petService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(petResponse);
    }

    @GetMapping
    public ResponseEntity<Page<PetResponseDto>> getAll(@RequestParam(defaultValue = "0", value = "page") int page, 
    @RequestParam(value = "size", defaultValue = "10") int size){
        return ResponseEntity.status(HttpStatus.OK).body(this.petService.getAll(page, size));                                   
    }
    
}
