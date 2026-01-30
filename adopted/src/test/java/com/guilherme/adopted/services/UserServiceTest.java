package com.guilherme.adopted.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.guilherme.adopted.dtos.UserRequestDto;
import com.guilherme.adopted.dtos.UserResponseDto;
import com.guilherme.adopted.exception.DuplicateDateException;
import com.guilherme.adopted.mapper.UserConverter;
import com.guilherme.adopted.models.User;
import com.guilherme.adopted.repositories.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserValidationService userValidation;

    @Captor
    private ArgumentCaptor<User> userCaptor;
   
    @Test
    void shouldCreateUserSuccessfully(){
        //ARRANGE
        UserRequestDto dto = new UserRequestDto(
            "Guilherme",
            LocalDate.of(1999, 2, 28),
            "gui@email.com",
            "119999999",
            "123"
        );

         User user = new User();

        UserResponseDto responseDto = new UserResponseDto(
            null, "Guilherme", null, dto.email(), dto.telephone(), null
        );

        //ACT
        when(userConverter.toEntity(dto)).thenReturn(user);
        when(passwordEncoder.encode(dto.password())).thenReturn("encrypted");
        when(userConverter.toResponse(user)).thenReturn(responseDto);

        UserResponseDto result = userService.create(dto);

        verify(userValidation).validateDuplicateData(dto.email());
        verify(userConverter).toEntity(dto);
        verify(passwordEncoder).encode(dto.password());
        verify(userRepository).save(userCaptor.capture());

        assertEquals("encrypted", userCaptor.getValue().getPassword());
        assertEquals(responseDto, result);
    }

    @Test
    void shoulThrowExceptionWhenEmailIsDuplicated(){

        UserRequestDto dto = new UserRequestDto(
        "Guilherme",
        LocalDate.of(1999, 2, 28),
        "gui@email.com",
        "119999999",
        "123"
        );

        doThrow(new DuplicateDateException("this email is already in use"))
        .when(userValidation)
        .validateDuplicateData(dto.email());

        assertThrows(DuplicateDateException.class,
            () -> userService.create(dto)
        );

        verify(userConverter, never()).toEntity(any());
        verify(passwordEncoder, never()).encode(any());
        verify(userRepository, never()).save(any());
    }
    
}
