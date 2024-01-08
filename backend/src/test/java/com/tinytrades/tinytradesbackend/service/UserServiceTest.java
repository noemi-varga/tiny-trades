package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.user.UserResponse;
import com.tinytrades.tinytradesbackend.mapper.UserMapper;
import com.tinytrades.tinytradesbackend.model.User;
import com.tinytrades.tinytradesbackend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    // @InjectMocks - problems if other dependencies?
    private UserService userService;

    @BeforeEach
    void setUp() {
        this.userService = new UserService(userRepository);
    }

    @DisplayName("JUnit test for findUserById method")
    @Test
    void givenUserId_whenFindUserById_thenReturnUserObject() {
        User testUser = User.builder()
                .id(1L)
                .username("testUser")
                .email("testuser@example.com")
                .password("testPassword")
                .firstName("John")
                .lastName("Doe")
                .location("Test City")
                .registrationDate(LocalDateTime.now())
                .phoneNumber("1234567890")
                .build();

        given(userRepository.findById(1L)).willReturn(Optional.of(testUser));

        User actual = userService.findUserById(testUser.getId());

        Assertions.assertEquals(testUser, actual);
    }

    @DisplayName("JUnit test for findUserById method throws Exception")
    @Test
    void givenUserId_whenFindUserById_thenThrowsException() {
        Long userId = 1L;
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        Assertions.assertThrows(
                NoSuchElementException.class, () -> userService.findUserById(userId)
        );
    }

    @DisplayName("JUnit test for findAllUsers method")
    @Test
    void givenUsersList_whenFindAllUsers_thenReturnUserResponseList() {
        User testUser = User.builder()
                .id(1L)
                .username("testUser")
                .email("testuser@example.com")
                .password("testPassword")
                .firstName("John")
                .lastName("Doe")
                .location("Test City")
                .registrationDate(LocalDateTime.now())
                .phoneNumber("1234567890")
                .build();

        User testUser2 = User.builder()
                .id(1L)
                .username("testUser2")
                .email("testuser@example.com")
                .password("testPassword")
                .firstName("Jakab")
                .lastName("Gipsz")
                .location("Test City")
                .registrationDate(LocalDateTime.now())
                .phoneNumber("1234567890")
                .build();

        given(userRepository.findAll()).willReturn(List.of(testUser, testUser2));

        List<UserResponse> actualUsers = userService.findAllUsers();

        List<UserResponse> expectedUsers = List.of(
                UserMapper.mapToUserResponse(testUser),
                UserMapper.mapToUserResponse(testUser2)
        );

        Assertions.assertEquals(expectedUsers, actualUsers);

    }

    @DisplayName("JUnit test for findAllUsers method if there aren't users in the repository")
    @Test
    void givenEmptyList_whenFindAllUsers_thenReturnEmptyList() {

        given(userRepository.findAll()).willReturn(List.of());

        List<UserResponse> actualUsers = userService.findAllUsers();

        List<UserResponse> expectedUsers = List.of();

        Assertions.assertEquals(expectedUsers, actualUsers);

    }
}