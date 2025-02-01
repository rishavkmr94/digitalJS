package com.learn.userauthentication.services;

import com.learn.userauthentication.models.User;
import org.antlr.v4.runtime.misc.Pair;

public interface IUserService {
    public User signUp(User user) throws Exception;

    public Pair<User, String> login(User user) throws Exception;
}
