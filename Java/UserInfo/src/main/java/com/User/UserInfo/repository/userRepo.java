package com.User.UserInfo.repository;

import java.util.List;

import com.User.UserInfo.model.User;

public interface userRepo {
    
   List<User> findall();
   User findById(int id);
   String create(User user);
   User update(int id,User user);
}
