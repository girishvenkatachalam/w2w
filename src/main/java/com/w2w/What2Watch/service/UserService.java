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
   private UserRepository userRepository;

    public ResponseEntity Register(UserDetails userDetails) {
        //TODO : take user preferences and then save to DB
        userDetailsRepository.save(userDetails);
        return Login(userDetails);
    }

    public ResponseEntity Login(UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        //TODO : redirect to dashboard
    }

    public boolean IsRegistered(UserDetails details) {
        List<UserDetails> userDetails = userDetailsRepository.findByEmailId(details.getEmailId());
        if (userDetails.size() == 0){
            return false;
        } else
            return true;
    }

    public User getUserByGivenEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException("User with email \"" + email + "\" not found");
        user.setPassword("");
        return user;
    }
}
