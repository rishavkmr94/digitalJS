package com.learn.bookmyshow;

import com.learn.bookmyshow.Exceptions.UserAlreadyPresentException;
import com.learn.bookmyshow.Exceptions.UserNotFoundException;
import com.learn.bookmyshow.models.ResponseType;
import com.learn.bookmyshow.models.User;
import com.learn.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //try register
        User user = new User();
        user.setUsername("admin1");
        user.setEmail("admin1@gmail.com");
        try {
            ResponseType responseType=userService.save(user);
            System.out.println(responseType);
        }
        catch (UserAlreadyPresentException e) {
            System.out.println(e.getMessage());
        }

        try{
            User user1= userService.findByEmail("admin1@gmail.com");
            System.out.println(user1.getUsername());
        }
        catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

}
