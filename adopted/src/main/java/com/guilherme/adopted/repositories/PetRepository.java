package com.guilherme.adopted.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.adopted.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

}
