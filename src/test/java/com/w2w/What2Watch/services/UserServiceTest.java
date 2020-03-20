package com.w2w.What2Watch.services;

import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.models.ProductionCompany;
import com.w2w.What2Watch.models.SpokenLanguage;
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

        Genre genre = new Genre();
        genre.id=12;
        genre.name="Adventure";

        when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.addGenreToUserByEmail("tanvi@movie.com",genre);
        Genre actualGenre= actualUser.getGenres().get(0);

        assertEquals("Tanvi", actualUser.getName());
        assertEquals("tanvi@movie.com", actualUser.getEmail());
        assertEquals(1, actualUser.getGenres().size());
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

        Genre genre = new Genre();
        genre.id=12;
        genre.name="Adventure";

        when(userRepository.save(user)).thenReturn(user);
        User actualUser = userService.addGenreToUserByEmail("tanvi@movie.com",genre);
        Genre actualGenre= actualUser.getGenres().get(0);

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(actualUser);


        Genre genreNext = new Genre();
        genreNext.id=12;
        genreNext.name="Adventure";

        when(userRepository.save(actualUser)).thenReturn(actualUser);

        User actualUserNext = userService.addGenreToUserByEmail("tanvi@movie.com",genreNext);
        Genre actualGenreNext= actualUserNext.getGenres().get(0);

        assertEquals("Tanvi", actualUserNext.getName());
        assertEquals("tanvi@movie.com", actualUserNext.getEmail());
        assertEquals(1, actualUserNext.getGenres().size());

        assertEquals(12, actualGenreNext.id);
        assertEquals("Adventure", actualGenreNext.name);

    }


    @Test
    void shouldAddLanguageToUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        User user=new User();
        user.userId="123";
        user.name="Tanvi";
        user.email="tanvi@movie.com";

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(user);

        SpokenLanguage spokenLanguage = new SpokenLanguage();
        spokenLanguage.iso_639_1="da";
        spokenLanguage.name="Dansk";

        when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.addLanguageToUserByEmail("tanvi@movie.com",spokenLanguage);
        SpokenLanguage actualLanguage= actualUser.getLanguages().get(0);

        assertEquals("Tanvi", actualUser.getName());
        assertEquals("tanvi@movie.com", actualUser.getEmail());
        assertEquals(1, actualUser.getLanguages().size());
        assertEquals("da", actualLanguage.iso_639_1);
        assertEquals("Dansk", actualLanguage.name);

    }

    @Test
    void shouldNotAddDuplicateLanguageToUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        User user=new User();
        user.userId="123";
        user.name="Tanvi";
        user.email="tanvi@movie.com";

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(user);

        SpokenLanguage spokenLanguage = new SpokenLanguage();
        spokenLanguage.iso_639_1="da";
        spokenLanguage.name="Dansk";

        when(userRepository.save(user)).thenReturn(user);
        User actualUser = userService.addLanguageToUserByEmail("tanvi@movie.com",spokenLanguage);
        //SpokenLanguage actualLanguage= actualUser.getLanguages().get(0);

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(actualUser);


        SpokenLanguage spokenLanguageNext = new SpokenLanguage();
        spokenLanguageNext.iso_639_1="da";
        spokenLanguageNext.name="Dansk";

        when(userRepository.save(actualUser)).thenReturn(actualUser);

        User actualUserNext = userService.addLanguageToUserByEmail("tanvi@movie.com",spokenLanguageNext);
        SpokenLanguage actualLanguageNext= actualUserNext.getLanguages().get(0);

        assertEquals("Tanvi", actualUserNext.getName());
        assertEquals("tanvi@movie.com", actualUserNext.getEmail());
        assertEquals(1, actualUserNext.getLanguages().size());

        assertEquals("da", actualLanguageNext.iso_639_1);
        assertEquals("Dansk", actualLanguageNext.name);

    }


    @Test
    void shouldAddProductionCompanyToUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        User user=new User();
        user.userId="123";
        user.name="Tanvi";
        user.email="tanvi@movie.com";

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(user);

        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.id=10717;
        productionCompany.name="Shamley Productions";

        when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.addProductionCompanyToUserByEmail("tanvi@movie.com",productionCompany);
        ProductionCompany actualproductionCompany= actualUser.getProduction_companies().get(0);

        assertEquals("Tanvi", actualUser.getName());
        assertEquals("tanvi@movie.com", actualUser.getEmail());
        assertEquals(1, actualUser.getProduction_companies().size());
        assertEquals(10717, actualproductionCompany.id);
        assertEquals("Shamley Productions", actualproductionCompany.name);

    }

    @Test
    void shouldNotAddDuplicateProductionCompanyToUserSuccessfullyByGivenEmail() throws UserNotFoundException {
        User user=new User();
        user.userId="123";
        user.name="Tanvi";
        user.email="tanvi@movie.com";

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(user);

        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.id=10717;
        productionCompany.name="Shamley Productions";

        when(userRepository.save(user)).thenReturn(user);
        User actualUser = userService.addProductionCompanyToUserByEmail("tanvi@movie.com",productionCompany);
       // SpokenLanguage actualProductionCompany= actualUser.getLanguages().get(0);

        when(userRepository.findByEmail("tanvi@movie.com")).thenReturn(actualUser);


        ProductionCompany productionCompanyNext = new ProductionCompany();
        productionCompanyNext.id=10717;
        productionCompanyNext.name="Shamley Productions";

        when(userRepository.save(actualUser)).thenReturn(actualUser);

        User actualUserNext = userService.addProductionCompanyToUserByEmail("tanvi@movie.com",productionCompanyNext);
        ProductionCompany actualProductionCompanyNext= actualUserNext.getProduction_companies().get(0);

        assertEquals("Tanvi", actualUserNext.getName());
        assertEquals("tanvi@movie.com", actualUserNext.getEmail());
        assertEquals(1, actualUserNext.getProduction_companies().size());

        assertEquals(10717, actualProductionCompanyNext.id);
        assertEquals("Shamley Productions", actualProductionCompanyNext.name);

    }

}
