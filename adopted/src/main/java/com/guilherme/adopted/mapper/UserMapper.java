package com.guilherme.adopted.mapper;

import org.springframework.stereotype.Component;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.interfaces.UserConverterInterface;
import com.guilherme.adopted.models.User;

@Component
public class UserMapper implements UserConverterInterface {

    @Override
    public User toEntity(UserRequestDto requestObject) {
        User user = new User();
        user.setName(requestObject.name());
        user.setDateOfBirth(requestObject.dateOfBirth());
        user.setEmail(requestObject.email());
        user.setPassword(requestObject.password());
        user.setTelephone(requestObject.telephone());
        return user;
    }

    @Override
    public UserResponseDto toResponse(User entityObject) {
        return new UserResponseDto(
            entityObject.getId(),
            entityObject.getName(),
            entityObject.getDateOfBirth(),
            entityObject.getEmail(),
            entityObject.getTelephone(),
            entityObject.getPassword()
        );
    }

}
