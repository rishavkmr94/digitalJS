package com.learn.userauthentication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "UserTable")
public class User extends BaseModel{
    private String email;
    private String password;
    @ManyToMany
    private List<Roles> roles = new ArrayList<>();
}
