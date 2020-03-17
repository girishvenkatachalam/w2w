package com.w2w.What2Watch;

import com.w2w.What2Watch.models.UserDetails;
import com.w2w.What2Watch.repositories.UserDetailsRepository;
import com.w2w.What2Watch.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Principal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class UserServiceImplTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDetailsRepository userDetailsRepository;

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

}
