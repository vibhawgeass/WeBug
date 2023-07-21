package com.example.userService.dao.userDaoImpl;

import com.example.userService.dao.UserDao;
import com.example.userService.model.User;
import com.example.userService.model.request.LoginRequest;
import com.example.userService.model.response.LoginResponse;
import com.example.userService.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql="select * from User";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User findById(int id) {
        String sql="select * from User where user_id=?";
        return (User)jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(User.class));
    }

    @Override
    public UserResponse findByEmail(String email) {
        String sql="select * from User where email=?";
        try {
            return (UserResponse) jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper(UserResponse.class));
        }
        catch(Exception e){
            UserResponse user=new UserResponse();
            user.setStatus("-1");
            user.setError("email not found");
            return user;
        }
    }

    @Override
    public UserResponse addUser(User user) {
        String checksql="select count(*) from User where email=?";
        int count=jdbcTemplate.queryForObject(checksql, Integer.class,user.getEmail());
        UserResponse response=new UserResponse();
        if(count==0) {
            String sql = "INSERT INTO user(first_name,last_name,email,password,organization) VALUES (?,?,?,?,?)";
            int a = jdbcTemplate.update(sql, user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(), user.getOrganization());

            if (a == 1) {
                response.setStatus("0");
                response.setMessage("Successfully added");
            } else {
                response.setStatus("-1");
                response.setMessage("some error occured");
            }
            return response;
        }
        else{
            response.setStatus("-1");
            response.setMessage("same email exists");
            return response;
        }
    }

    @Override
    public int deleteUser(int id) {
        String sql="delete from user where user_id=?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateUser(int id, User user) {
        String sql="update user set first_name=?,last_name=?,email=?,password=?,organization=? where user_id=? ";
        return jdbcTemplate.update(sql,user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword(),user.getOrganization(),user.getUser_id());
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response=new LoginResponse();
        String sqlCount="select count(*) from user where email=? and password=?";
        int count=jdbcTemplate.queryForObject(sqlCount, Integer.class,request.getEmail(),request.getPassword());
        if(count>0){
            String sql="select * from User where email=? and password=?";
            User result= (User)jdbcTemplate.queryForObject(sql, new Object[]{request.getEmail(),request.getPassword()}, new BeanPropertyRowMapper(User.class));
            response.setStatus("0");
            response.setUser_id(result.getUser_id());
            return response;
        }
        else{
            response.setStatus("-1");
            response.setUser_id(-1);
            return response;
        }

    }
}
