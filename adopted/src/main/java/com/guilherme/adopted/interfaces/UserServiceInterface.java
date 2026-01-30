package com.guilherme.adopted.interfaces;

import java.util.UUID;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.dtos.UserUpdateDto;

public interface UserServiceInterface extends CrudService<UserRequestDto, UserResponseDto, UserUpdateDto, UUID> {

    UserResponseDto getByEmail(String email);

}
