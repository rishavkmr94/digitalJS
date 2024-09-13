package com.learn.bookmyshow.controllers;

import com.learn.bookmyshow.Exceptions.UserAlreadyPresentException;
import com.learn.bookmyshow.Exceptions.UserNotFoundException;
import com.learn.bookmyshow.models.ResponseType;
import com.learn.bookmyshow.models.User;
import com.learn.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {
    //new user should register
    //existing user can login
    //if new user tries to login, exception usernotfound
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void registerUser(User user) throws UserAlreadyPresentException {
        ResponseType response = userService.save(user);
        System.out.println(response);
    }

    public void loginUser(String email) throws UserNotFoundException{
        User user = userService.findByEmail(email);
        System.out.println(user);
    }
}
