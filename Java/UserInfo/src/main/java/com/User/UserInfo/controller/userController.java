package com.User.UserInfo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.User.UserInfo.model.User;
import com.User.UserInfo.repository.userRepo;

@Controller
@RequestMapping("/api/user")
public class userController {
    
    private final userRepo userrepo;

    public userController(userRepo userrepo){
        this.userrepo = userrepo ;
    }

    //good practice to return response entity always
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userrepo.findall();
        if(users.isEmpty())
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id){
        User user = userrepo.findById(id);
        if(user != null)
        return new ResponseEntity<>(user,HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);     
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        String result =  userrepo.create(user);
        return new ResponseEntity<String>(result,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        User existingUser =userrepo.update(id, user);
        if(existingUser != null)
        return new ResponseEntity<>(existingUser,HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
