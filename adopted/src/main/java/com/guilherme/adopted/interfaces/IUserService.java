package com.guilherme.adopted.interfaces;

import java.util.List;
import java.util.UUID;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.dtos.UserUpdateDto;

public interface IUserService {

    UserResponseDto create(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto update(UserUpdateDto userUpdateDto, String email);

    UserResponseDto deleteById(UUID id);

    UserResponseDto deleteByEmail(String email);
}
