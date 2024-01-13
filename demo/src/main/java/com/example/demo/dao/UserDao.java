package com.example.demo.dao;

import com.example.demo.entity.User;

public interface UserDao {
    User findByUserName(String user_name);
    void save(User user);
}
