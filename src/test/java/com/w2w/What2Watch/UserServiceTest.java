package com.w2w.What2Watch;

import com.w2w.What2Watch.Exception.UserNotFoundException;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.models.UserDetails;
import com.w2w.What2Watch.repositories.UserDetailsRepository;
import com.w2w.What2Watch.repositories.UserRepository;
import com.w2w.What2Watch.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Principal principal;

    UserDetails userDetails;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        userDetails = new UserDetails("12", "abc@example.com", "abc");

    }
    @Test
    public void IsRegisteredTest()
    {
        //TODO
    }

    @Test
    void shouldGetUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        UserRepository userRepositorymock = mock(UserRepository.class);

        when(userRepositorymock.findByEmail("tanvi@movie.com")).thenReturn(new User("Tanvi","tanvi@movie.com","123456"));

        UserService userService = new UserService(userRepositorymock);
        User user = userService.getUserByGivenEmail("tanvi@movie.com");

        assertEquals("Tanvi", user.getName());
        assertEquals("tanvi@movie.com", user.getEmail());

    }

    @Test
    void shouldNotGetUserByGivenEmailWhenUserIsNotPresent() {
        UserRepository userRepositorymock = mock(UserRepository.class);

        when(userRepositorymock.findByEmail("tanvi@movie.com")).thenReturn(new User("Tanvi","tanvi@movie.com","123456"));

        UserService userService = new UserService(userRepositorymock);

        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.getUserByGivenEmail("asd@qwqw"));
        assertEquals("User with email \"asd@qwqw\" not found", exception.getMessage());
    }

}
