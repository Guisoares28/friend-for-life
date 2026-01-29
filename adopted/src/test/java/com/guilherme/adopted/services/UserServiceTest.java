package com.guilherme.adopted.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserConverter userConverter;

    @Captor
    private ArgumentCaptor<User> usercaptor;

    @Test
    void shouldReturnDuplicateDateExceptionWhenSavingADuplicateUser(){
        UserRequestDto userRequesDto = new UserRequestDto(
            "guilherme soares",
            LocalDate.of(1999, 2, 28),
            "gui.soares2899@gmail.com",
            "11994210778",
            "gui123"
        );

        when(userRepository.existsByEmail(userRequesDto.email()))
        .thenReturn(true);

        DuplicateDateException exception = 
        assertThrows(
            DuplicateDateException.class,
        () -> userService.create(userRequesDto));

        assertEquals("this email is already in use", exception.getMessage());

        verify(userRepository, never()).save(any());
        verify(userConverter, never()).toEntity(any());
        verify(bCryptPasswordEncoder, never()).encode(any());
    }

    @Test
    void shouldSaveTheUserInTheDatabase(){
        UserRequestDto userRequesDto = new UserRequestDto(
            "guilherme soares",
            LocalDate.of(1999, 2, 28),
            "gui.soares2899@gmail.com",
            "11994210778",
            "gui123"
        );

        when(userRepository.existsByEmail(userRequesDto.email())).thenReturn(false);
        when(userConverter.toEntity(userRequesDto)).thenReturn(new User());
        when(bCryptPasswordEncoder.encode(userRequesDto.password())).thenReturn(userRequesDto.password().concat("encripted"));

        userService.create(userRequesDto);

        verify(userRepository).save(usercaptor.capture());
        verify(userConverter).toEntity(userRequesDto);
        verify(bCryptPasswordEncoder).encode(userRequesDto.password());

        User userSaved = usercaptor.getValue();

        assertEquals("gui123encripted", userSaved.getPassword());

    }
}
