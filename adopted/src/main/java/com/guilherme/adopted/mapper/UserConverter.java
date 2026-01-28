package com.guilherme.adopted.mapper;

import org.springframework.stereotype.Component;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.dtos.UserUpdateDto;
import com.guilherme.adopted.interfaces.IConverter;
import com.guilherme.adopted.interfaces.IUpdateConversor;
import com.guilherme.adopted.models.User;

@Component
public class UserConverter implements IConverter<UserRequestDto, User, UserResponseDto>, IUpdateConversor<UserUpdateDto, User> {

    @Override
    public User toEntity(UserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.name());
        user.setDateOfBirth(requestDto.dateOfBirth());
        user.setEmail(requestDto.email());
        user.setPassword(requestDto.password());
        user.setTelephone(requestDto.telephone());
        return user;
    }

    @Override
    public UserResponseDto toResponse(User entity) {
        UserResponseDto userResponseDto = new UserResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getDateOfBirth(),
            entity.getEmail(),
            entity.getTelephone(),
            entity.getPassword()
        );
        return userResponseDto;
    }

    @Override
    public void toEntityUpdate(UserUpdateDto updateDto, User entity) {

        if(updateDto.name() != null){
            entity.setName(updateDto.name());
        }

        if(updateDto.dateOfBirth() != null){
            entity.setDateOfBirth(updateDto.dateOfBirth());
        }

        if(updateDto.telephone() != null){
            entity.setTelephone(updateDto.telephone());
        }
    
    }

}
