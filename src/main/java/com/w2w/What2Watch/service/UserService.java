package com.w2w.What2Watch.service;

import com.w2w.What2Watch.Exception.UserNotFoundException;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.models.UserDetails;
import com.w2w.What2Watch.repositories.UserDetailsRepository;
import com.w2w.What2Watch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    //TODO : need to handle exceptions
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity Register(Principal principal) {
        //TODO : take user preferences and then save to DB
        UserDetails userDetails = ConvertPrincipalIntoUserDetails(principal);
        userDetailsRepository.save(userDetails);
        return Login(principal);
    }

    public ResponseEntity Login(Principal principal) {
        UserDetails userDetails = ConvertPrincipalIntoUserDetails(principal);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        //TODO : redirect to dashboard
    }

    public boolean IsRegistered(Principal principal) {
        UserDetails details = ConvertPrincipalIntoUserDetails(principal);
        List<UserDetails> userDetails = userDetailsRepository.findByEmailId(details.getEmailId());
        if (userDetails.size() == 0){
            return false;
        } else
            return true;
    }

    public User getUserByGivenEmail(String email) throws UserNotFoundException {
        if (userRepository.findByEmail(email).equals(null))
            throw new UserNotFoundException("User with email :" + email + " not found");
        User user = userRepository.findByEmail(email);
        user.setPassword("");
        return user;
    }

    public UserDetails ConvertPrincipalIntoUserDetails(Principal principal)
    {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        Map<String, String> details = new LinkedHashMap<>();
        details = (Map<String, String>) authentication.getDetails();
        UserDetails userDetails = new UserDetails(details.get("id"), details.get("email"),details.get("name"));
        return userDetails;
    }
}
