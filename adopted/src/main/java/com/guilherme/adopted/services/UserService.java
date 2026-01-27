package com.guilherme.adopted.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.dtos.UserUpdateDto;
import com.guilherme.adopted.interfaces.IUserService;
import com.guilherme.adopted.repositories.UserRepository;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public UserResponseDto update(UserUpdateDto userUpdateDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public UserResponseDto deleteById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public UserResponseDto deleteByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByEmail'");
    }

}
