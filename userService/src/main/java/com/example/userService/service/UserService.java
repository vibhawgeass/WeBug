package com.example.userService.service;

import com.example.userService.dao.userDaoImpl.UserDaoImpl;
import com.example.userService.model.User;
import com.example.userService.model.request.LoginRequest;
import com.example.userService.model.response.LoginResponse;
import com.example.userService.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDaoImpl userDaoImpl;

    public List<User> getAll(){
        return userDaoImpl.findAll();
    }

    public User getById(int id){
        return userDaoImpl.findById(id);
    }
    public UserResponse getByEmail(String email){
        return userDaoImpl.findByEmail(email);
    }

    public UserResponse addUser(User user){
        return userDaoImpl.addUser(user);
    }

    public int deleteUser(int id){
        return userDaoImpl.deleteUser(id);
    }

    public int updateUser(int id, User user){
        return userDaoImpl.updateUser(id,user);
    }

    public LoginResponse login(LoginRequest request){
        return userDaoImpl.login(request);
    }
}
