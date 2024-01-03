package com.tinytrades.tinytradesbackend.service;

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

        Assertions.assertEquals(actual, testUser);
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
}