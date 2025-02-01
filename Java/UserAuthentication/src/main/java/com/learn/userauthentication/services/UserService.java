package com.learn.userauthentication.services;

import com.learn.userauthentication.exceptions.InvalidPasswordException;
import com.learn.userauthentication.exceptions.UserAlreadyExistsException;
import com.learn.userauthentication.exceptions.UserDoesNotExistException;
import com.learn.userauthentication.models.User;
import com.learn.userauthentication.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(User user) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("user already exists, try logging in");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Pair<User, String> login(User user) throws InvalidPasswordException, UserDoesNotExistException {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            String storedPassword = userOptional.get().getPassword();
            String userPassword = user.getPassword();
            if (!bCryptPasswordEncoder.matches(userPassword, storedPassword)) {
                System.out.println("Wrong password");
                System.out.println(storedPassword+" "+storedPassword);
                throw new InvalidPasswordException("Invalid password");
            }
            else{
                Map<String, Object> payload = new HashMap<>();
                long timeInMillis = System.currentTimeMillis();
                payload.put("IAT", timeInMillis);
                payload.put("expiry",timeInMillis+600000);
                payload.put("userId", user.getId());
                payload.put("iss", "rishav");

                MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
                SecretKey secretKey = macAlgorithm.key().build();
                String token = Jwts.builder().claims(payload).signWith(secretKey).compact();
                return new Pair<>(userOptional.get(),token);
            }
        }
        else{
            throw new UserDoesNotExistException("User doesn't exist, please signup");
        }
    }
}
