package com.example.userService.controller;

import com.example.userService.model.User;
import com.example.userService.model.request.LoginRequest;
import com.example.userService.model.response.LoginResponse;
import com.example.userService.model.response.UserResponse;
import com.example.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/getuser")
    public List<User> getUser(){
        return userService.getAll();
    }

    @GetMapping("/getuserbyid/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getById(id);
    }

    @GetMapping("/getuserbymail/{email}")
    public UserResponse getUserByEmail(@PathVariable("email") String email){
        return userService.getByEmail(email);
    }

    @PostMapping("/validatelogin")
    public LoginResponse validateLogin(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @PostMapping("/adduser")
    public UserResponse addUser(@RequestBody User user){
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
