package com.guilherme.adopted.services;

import org.springframework.stereotype.Service;

import com.guilherme.adopted.exception.DuplicateDateException;
import com.guilherme.adopted.interfaces.IUserValidation;
import com.guilherme.adopted.repositories.UserRepository;

@Service
public class UserValidationService implements IUserValidation {

    private UserRepository userRepository;

    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validateDuplicateData(String email) {
        if(this.userRepository.existsByEmail(email)){
            throw new DuplicateDateException("this email is already in use");
        }
    }

}
