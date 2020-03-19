package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.What2WatchApplication;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ What2WatchApplication.class })
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userServiceMock;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void shouldGetUserSuccessfullyByGivenEmail() throws Exception {
        when(userServiceMock.getUserByGivenEmail("tanvi@movie.com")).thenReturn(new User("123","Tanvi", "tanvi@movie.com"));

        mockMvc.perform(get("/user/tanvi@movie.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.userId").value("123"))
                .andExpect(jsonPath("$.name").value("Tanvi"))
                .andExpect(jsonPath("$.email").value("tanvi@movie.com"));

        verify(userServiceMock).getUserByGivenEmail("tanvi@movie.com");
    }

    @Test
    public void shouldNotGetAWalletSuccessfullyByGivenId() throws Exception {
        User user=new User();
        when(userServiceMock.getUserByGivenEmail("abc@xyz")).thenReturn(user);

        mockMvc.perform(get("/user/abc@xyz")
                .content("null")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

        verify(userServiceMock).getUserByGivenEmail("abc@xyz");
    }

}
