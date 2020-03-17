package com.w2w.What2Watch.Controller;

import com.w2w.What2Watch.Exception.UserNotFoundException;
import com.w2w.What2Watch.Service.UserService;
import com.w2w.What2Watch.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByGivenEmail(@PathVariable("email") @NotEmpty String email) throws  UserNotFoundException {
        return userService.getUserByGivenEmail(email);
    }
}
