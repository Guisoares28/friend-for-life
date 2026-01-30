package com.guilherme.adopted.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.dtos.UserUpdateDto;
import com.guilherme.adopted.exception.NoInformationFoundException;
import com.guilherme.adopted.interfaces.IUserValidation;
import com.guilherme.adopted.interfaces.UserServiceInterface;
import com.guilherme.adopted.mapper.UserConverter;
import com.guilherme.adopted.models.User;
import com.guilherme.adopted.repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserConverter userConverter;

    private IUserValidation userValidation;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserConverter userConverter,
        IUserValidation userValidation
    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConverter = userConverter;
        this.userValidation = userValidation;
    }

    @Override
    public UserResponseDto create(UserRequestDto requestObject) {
        this.userValidation.validateDuplicateData(requestObject.email()); //Lança uma DuplicateException;

        User user = userConverter.toEntity(requestObject);
        user.setPassword(bCryptPasswordEncoder.encode(requestObject.password()));
        userRepository.save(user);
        return userConverter.toResponse(user);
    }

    @Override
    public UserResponseDto get(UUID id) {
        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty()){
            throw new NoInformationFoundException("No users found");
        }

        return userConverter.toResponse(user.get());
    }

     @Override
    public UserResponseDto getByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new NoInformationFoundException("No users found");
        }

        return userConverter.toResponse(user.get());
    }

    @Override
    public List<UserResponseDto> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

     @Override
    public UserResponseDto update(UserUpdateDto updateObject, UUID id) {
        User user = this.userRepository.findById(id)
        .orElseThrow(() -> new NoInformationFoundException("No users found"));

        Optional.ofNullable(updateObject.name()).ifPresent(user::setName);
        Optional.ofNullable(updateObject.dateOfBirth()).ifPresent(user::setDateOfBirth);
        Optional.ofNullable(updateObject.telephone()).ifPresent(user::setTelephone);
        
        this.userRepository.save(user);

        return this.userConverter.toResponse(user);
    }
   

    @Override
    public UserResponseDto delete(UUID id) {
        User user = this.userRepository.findById(id)
        .orElseThrow(() -> new NoInformationFoundException("No users found"));

        this.userRepository.deleteById(id);

        return this.userConverter.toResponse(user);
    }

}
