package com.guilherme.adopted.interfaces;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.models.User;

public interface UserConverterInterface extends ConverterInterface<UserRequestDto, User, UserResponseDto> {
}
