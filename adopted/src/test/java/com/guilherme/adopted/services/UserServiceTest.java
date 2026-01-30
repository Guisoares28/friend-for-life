package com.guilherme.adopted.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.guilherme.adopted.mapper.UserMapper;
import com.guilherme.adopted.models.User;
import com.guilherme.adopted.repositories.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

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
         user.setEmail(dto.email());

        UserResponseDto responseDto = new UserResponseDto(
            null, "Guilherme", null, dto.email(), dto.telephone(), null
        );

        when(this.userRepository.existsByEmail(dto.email())).thenReturn(false);
        when(this.userMapper.toEntity(dto)).thenReturn(user);
        when(this.userMapper.toResponse(user)).thenReturn(responseDto);
        when(passwordEncoder.encode(anyString())).thenReturn("123-encrypted");


        userService.create(dto);

        verify(passwordEncoder).encode(anyString());
        verify(this.userRepository).save(userCaptor.capture());

        assertEquals("gui@email.com", userCaptor.getValue().getEmail());
        assertEquals("123-encrypted", userCaptor.getValue().getPassword());
    }

    @Test
    void shoulThrowExceptionWhenEmailIsDuplicated(){
    }
    
}
