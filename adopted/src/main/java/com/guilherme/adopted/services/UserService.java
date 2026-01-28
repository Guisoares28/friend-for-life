package com.guilherme.adopted.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.dtos.UserUpdateDto;
import com.guilherme.adopted.exception.DuplicateDateException;
import com.guilherme.adopted.interfaces.IUserService;
import com.guilherme.adopted.mapper.UserConverter;
import com.guilherme.adopted.models.User;
import com.guilherme.adopted.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserConverter userConverter;

    
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConverter = userConverter;
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {

        if(this.userRepository.existsByEmail(userRequestDto.email())){
            throw new DuplicateDateException("this email is already in use");
        }

        User user = userConverter.toEntity(userRequestDto);
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.password()));
        userRepository.save(user);
        return userConverter.toResponse(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    @Transactional
    public UserResponseDto update(UserUpdateDto userUpdateDto, String email) {
        Optional<User> user = this.userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new RuntimeException();
        }

        this.userConverter.toEntityUpdate(userUpdateDto, user.get());
        this.userRepository.save(user.get());

        return this.userConverter.toResponse(user.get());
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
