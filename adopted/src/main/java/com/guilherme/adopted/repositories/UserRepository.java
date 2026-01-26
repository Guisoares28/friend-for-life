package com.guilherme.adopted.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.adopted.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
