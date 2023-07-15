package com.example.userService.dao.userDaoImpl;

import com.example.userService.dao.UserDao;
import com.example.userService.model.User;
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
    public int addUser(User user) {
        String sql="INSERT INTO user(first_name,last_name,email,password,organization) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword(),user.getOrganization());
    }

    @Override
    public int deleteUser(int id) {
        String sql="delete from user where id=?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateUser(int id, User user) {
        String sql="update user set first_name=?,last_name=?,email=?,password=?,organization=? where user_id=? ";
        return jdbcTemplate.update(sql,user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword(),user.getOrganization(),user.getUser_id());
    }
}
