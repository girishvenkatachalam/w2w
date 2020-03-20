package com.w2w.What2Watch.services;

import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
        userDetails = new User("123","abc", "abc@example.com");
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
        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(new User("123","Tanvi","tanvi@movie.com"));

        User user = userService.getUserByGivenEmail("tanvi@movie.com");

        assertEquals("Tanvi", user.getName());
        assertEquals("tanvi@movie.com", user.getEmail());

    }

    @Test
    void shouldNotGetUserByGivenEmailWhenUserIsNotPresent() {
        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(new User("123","Tanvi","tanvi@movie.com"));

        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.getUserByGivenEmail("asd@qwqw"));
        assertEquals("User with email \"asd@qwqw\" not found", exception.getMessage());
    }

    @Test
    void shouldAddGenreToUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        User user=new User();
        user.userId="123";
        user.name="Tanvi";
        user.email="tanvi@movie.com";

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(user);
        List<Genre> genres=user.getGenres();
        Genre genre = new Genre();
        genre.id=12;
        genre.name="Adventure";

        when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.addGenreToUserByEmail("tanvi@movie.com",genre);
        Genre actualGenre= actualUser.getGenres().get(0);

        assertEquals("Tanvi", actualUser.getName());
        assertEquals("tanvi@movie.com", actualUser.getEmail());
        assertEquals(1, user.getGenres().size());
        assertEquals(12, actualGenre.id);
        assertEquals("Adventure", actualGenre.name);

    }

    @Test
    void shouldNotAddDuplicateGenreToUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        User user=new User();
        user.userId="123";
        user.name="Tanvi";
        user.email="tanvi@movie.com";

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(user);
        List<Genre> genres=user.getGenres();
        Genre genre = new Genre();
        genre.id=12;
        genre.name="Adventure";

        when(userRepository.save(user)).thenReturn(user);
        User actualUser = userService.addGenreToUserByEmail("tanvi@movie.com",genre);
        Genre actualGenre= actualUser.getGenres().get(0);

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(actualUser);

        List<Genre> genresNext=actualUser.getGenres();
        Genre genreNext = new Genre();
        genreNext.id=12;
        genreNext.name="Adventure";

        when(userRepository.save(actualUser)).thenReturn(actualUser);

        User actualUserNext = userService.addGenreToUserByEmail("tanvi@movie.com",genreNext);
        Genre actualGenreNext= actualUserNext.getGenres().get(0);

        assertEquals("Tanvi", actualUser.getName());
        assertEquals("tanvi@movie.com", actualUser.getEmail());
        assertEquals(1, user.getGenres().size());

        assertEquals(12, actualGenre.id);
        assertEquals("Adventure", actualGenre.name);

    }
}
