package com.example.userService.dao;

import com.example.userService.model.User;

import java.util.List;

public interface  UserDao {

    List<User> findAll();
    User findById(int id);
    int addUser(User user);
    int deleteUser(int id);
    int updateUser(int id, User user);

}
