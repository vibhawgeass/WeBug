package com.example.userService.controller;

import com.example.userService.model.User;
import com.example.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getuser")
    public List<User> getUser(){
        return userService.getAll();
    }

    @GetMapping("/getuserbyid/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getById(id);
    }

    @PostMapping("/adduser")
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public int deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PutMapping("/updateuser/{id}")
    public int updateUser(@PathVariable int id,@RequestBody User user){
        return userService.updateUser(id,user);
    }
}
