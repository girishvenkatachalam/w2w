package com.w2w.What2Watch.services;

import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    User userDetails;

    @BeforeEach
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        userDetails = new User("abc", "abc@example.com");
    }
    @Test
    public void IsRegisteredTrueTest()
    {
        when(userRepository.findByEmail("abc@example.com")).thenReturn(userDetails);
        assertTrue(userService.IsRegistered(userDetails));
    }

    @Test
    public void IsRegisteredFalseTest()
    {
        //TODO
        User user = null;
        when(userRepository.findByEmail("abc@example.com")).thenReturn(user);
        assertFalse(userService.IsRegistered(userDetails));
    }

    @Test
    public void RegisterUserTest(){
        userService.Register(userDetails);
        verify(userRepository,times(1)).save(userDetails);
    }

    @Test
    public void LoginuserTest(){
        ResponseEntity responseEntity = userService.Login(userDetails);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldGetUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(new User("Tanvi","tanvi@movie.com"));

        User user = userService.getUserByGivenEmail("tanvi@movie.com");

        assertEquals("Tanvi", user.getName());
        assertEquals("tanvi@movie.com", user.getEmail());

    }

    @Test
    void shouldNotGetUserByGivenEmailWhenUserIsNotPresent() {
        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(new User("Tanvi","tanvi@movie.com"));

        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.getUserByGivenEmail("asd@qwqw"));
        assertEquals("User with email \"asd@qwqw\" not found", exception.getMessage());
    }

}
