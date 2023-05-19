package com.learnJS.springbootpj.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String firstName;
    private String lastName;
    private String DOB;
    private String email;
}