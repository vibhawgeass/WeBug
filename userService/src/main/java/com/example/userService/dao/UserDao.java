package com.example.userService.dao;

import com.example.userService.model.User;
import com.example.userService.model.request.LoginRequest;
import com.example.userService.model.response.LoginResponse;
import com.example.userService.model.response.UserResponse;

import java.util.List;

public interface  UserDao {

    List<User> findAll();
    User findById(int id);
    UserResponse findByEmail(String email);
    UserResponse addUser(User user);
    int deleteUser(int id);
    int updateUser(int id, User user);

    LoginResponse login(LoginRequest request);

}
