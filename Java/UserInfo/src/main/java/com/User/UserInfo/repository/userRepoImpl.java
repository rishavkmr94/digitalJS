package com.User.UserInfo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.User.UserInfo.model.User;

@Component
public class userRepoImpl implements userRepo{

    List<User> users = new ArrayList<>();

    @Override
    public List<User> findall() {
        return users;
    }

    @Override
    public User findById(int id) {
        Optional<User> result = users.stream().filter(user -> user.getId() == id).findFirst();
        return result.orElse(null);
    }

    @Override
    public String create(User user) {
        users.add(user);
        return "User created successfully";
    }

    @Override
    public User update(int id, User user) {
        User existingUser = findById(id);
        if(existingUser != null){
            existingUser.setEmail(user.getEmail());
            existingUser.setFName(user.getFName());
            existingUser.setLName(user.getLName());
            return existingUser;
        }
        return null;
    }
    
}
