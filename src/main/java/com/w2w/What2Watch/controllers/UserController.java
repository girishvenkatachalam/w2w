package com.w2w.What2Watch.controllers;

import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.Genre;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public ResponseEntity userGetLoginDetails(Principal principal) {

        ResponseEntity response;
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        Map<String, String> details = new LinkedHashMap<>();
        details = (Map<String, String>) authentication.getDetails();
        User userDetails;
        if (details.get("picture") == null)
            userDetails = new User(details.get("id"),details.get("name"), details.get("email"));
        else
            userDetails = new User(details.get("id"),details.get("name"), details.get("email"),details.get("picture"));
        if(!userService.IsRegistered(userDetails))
        {
            response = userService.Register(userDetails);
        } else {
            response = userService.Login(userDetails);            ;
        }
        return response;
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByGivenEmail(@PathVariable("email") @NotEmpty String email) throws UserNotFoundException {
        return userService.getUserByGivenEmail(email);
    }

    @PutMapping("/genre/{email}")
    @ResponseStatus(value = HttpStatus.OK)
    public User updateWalletByGivenId(@PathVariable(value = "email")String email, @RequestBody Genre genre) throws UserNotFoundException {
        User user = userService.updateUserGenreByEmail(email, genre);
        return user;
    }

}
