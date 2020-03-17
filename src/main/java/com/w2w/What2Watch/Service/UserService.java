package com.w2w.What2Watch.Service;

import com.w2w.What2Watch.Exception.UserNotFoundException;
import com.w2w.What2Watch.models.User;
import com.w2w.What2Watch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserByGivenEmail(String email) throws UserNotFoundException {
        if (userRepository.findByEmail(email).equals(null))
            throw new UserNotFoundException("User with email :" + email + " not found");
        User user = userRepository.findByEmail(email);
        user.setPassword("");
        return user;
    }
}
