package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.user.NewUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.UpdateUserRequest;
import com.tinytrades.tinytradesbackend.dto.user.UserResponse;
import com.tinytrades.tinytradesbackend.model.user.User;
import com.tinytrades.tinytradesbackend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

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
                .registrationDate(LocalDateTime.of(2024, 1, 11, 10, 0, 0))
                .phoneNumber("1234567890")
                .build();

        User testUser2 = User.builder()
                .id(2L)
                .username("testUser2")
                .email("testuser@example.com")
                .password("testPassword")
                .firstName("Jakab")
                .lastName("Gipsz")
                .location("Test City")
                .registrationDate(LocalDateTime.of(2024, 1, 11, 10, 0, 0))
                .phoneNumber("1234567890")
                .build();

        given(userRepository.findAll()).willReturn(List.of(testUser, testUser2));

        List<UserResponse> actualUsers = userService.findAllUsers();

        List<UserResponse> expectedUsers = List.of(
                new UserResponse(1L, "testUser"),
                new UserResponse(2L, "testUser2")
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

    @DisplayName("JUnit test for addUser method")
    @Test
    void givenNewUserRequest_whenAddUser_thenReturnUserResponse() {
        NewUserRequest newUserRequest = new NewUserRequest("newUser", "newEmail@example.com", "newPassword", "New", "User", "New City", "123456789");

        User user = User.builder()
                .id(1L)
                .username("newUser")
                .email("newEmail@example.com")
                .password("newPassword")
                .firstName("New")
                .lastName("User")
                .location("New City")
                .registrationDate(LocalDateTime.of(2024, 1, 11, 10, 0, 0))
                .phoneNumber("123456789")
                .build();

        given(userRepository.save(any(User.class))).willReturn(user);

        UserResponse actualResponse = userService.addUser(newUserRequest);

        UserResponse expectedResponse = new UserResponse(1L, "newUser");

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @DisplayName("JUnit test for updateUserById method")
    @Test
    void givenUpdateUserRequest_whenUpdateUserById_thenReturnUpdatedUserResponse() {
        Long userId = 1L;
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("updatedUsername", "Updated", "User", "updatedEmail@example.com", "updatedPassword", "Updated City", "0987654321");

        User user = User.builder()
                .id(userId)
                .username("existingUser")
                .email("existingEmail@example.com")
                .password("existingPassword")
                .firstName("Existing")
                .lastName("User")
                .location("Existing City")
                .registrationDate(LocalDateTime.of(2024, 1, 11, 10, 0, 0))
                .phoneNumber("1234567890")
                .build();

        User updatedUser = User.builder()
                .id(userId)
                .username("existingUser")
                .email("existingEmail@example.com")
                .password("existingPassword")
                .firstName("Existing")
                .lastName("User")
                .location("Existing City")
                .registrationDate(LocalDateTime.of(2024, 1, 11, 10, 0, 0))
                .phoneNumber("1234567890")
                .build();

        updatedUser.setUsername(updateUserRequest.username());
        updatedUser.setFirstName(updateUserRequest.firstName());
        updatedUser.setLastName(updateUserRequest.lastName());
        updatedUser.setEmail(updateUserRequest.email());
        updatedUser.setPassword(updateUserRequest.password());
        updatedUser.setLocation(updateUserRequest.location());
        updatedUser.setPhoneNumber(updateUserRequest.phoneNumber());

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(userRepository.save(user)).willReturn(updatedUser);

        UserResponse actualResponse = userService.updateUserById(userId, updateUserRequest);

        UserResponse expectedResponse = new UserResponse(1L, "updatedUsername");

        Assertions.assertEquals(expectedResponse, actualResponse);
    }


}