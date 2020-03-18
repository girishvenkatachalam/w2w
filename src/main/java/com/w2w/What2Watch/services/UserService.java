package com.w2w.What2Watch.services;

import com.w2w.What2Watch.exceptions.UserNotFoundException;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //TODO : need to handle exceptions
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity Register(User userDetails) {
        //TODO : take user preferences and then save to DB
        userRepository.save(userDetails);
        return Login(userDetails);
    }

    public ResponseEntity Login(User userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        //TODO : redirect to dashboard
    }

    public boolean IsRegistered(User details) {
        User userDetails = userRepository.findByEmail(details.getEmail());
        if (userDetails == null){
            return false;
        } else
            return true;
    }

    public User getUserByGivenEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException("User with email \"" + email + "\" not found");
        return user;
    }
}
