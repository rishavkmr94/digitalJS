package com.learn.bookmyshow.services;

import com.learn.bookmyshow.Exceptions.UserAlreadyPresentException;
import com.learn.bookmyshow.Exceptions.UserNotFoundException;
import com.learn.bookmyshow.models.ResponseType;
import com.learn.bookmyshow.models.User;
import com.learn.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseType save(User user) throws UserAlreadyPresentException {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyPresentException("user present, please try login");
        }
        User user1 =userRepository.save(user);
        if(user1 != null) {
            return ResponseType.SUCCESS;
        }
        return ResponseType.FAILED;
    }

    public User findByEmail(String email) throws UserNotFoundException {
        Optional<User> oUser= userRepository.findByEmail(email);
        if(oUser.isPresent()) {
            return oUser.get();
        }
        else throw new UserNotFoundException("User not found");

    }

}
