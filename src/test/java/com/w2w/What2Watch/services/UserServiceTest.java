package com.w2w.What2Watch.services;

import com.w2w.What2Watch.models.UserDetails;
import com.w2w.What2Watch.repositories.UserDetailsRepository;
import com.w2w.What2Watch.service.UserService;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDetailsRepository userDetailsRepository;

    UserDetails userDetails;

    List<UserDetails> users = new ArrayList<>();

    @BeforeEach
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        userDetails = new UserDetails("12", "abc@example.com", "abc");
    }
    @Test
    public void IsRegisteredTrueTest()
    {
        users.add(userDetails);
        when(userDetailsRepository.findByEmailId("abc@example.com")).thenReturn(users);
        assertTrue(userService.IsRegistered(userDetails));
    }

    @Test
    public void IsRegisteredFalseTest()
    {
        //TODO
        when(userDetailsRepository.findByEmailId("abc@example.com")).thenReturn(users);
        assertFalse(userService.IsRegistered(userDetails));
    }

    @Test
    public void RegisterUserTest(){
        userService.Register(userDetails);
        verify(userDetailsRepository,times(1)).save(userDetails);
    }

    @Test
    public void LoginuserTest(){
        ResponseEntity responseEntity = userService.Login(userDetails);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
