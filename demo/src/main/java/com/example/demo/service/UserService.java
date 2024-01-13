package com.example.demo.service;


import com.example.demo.entity.User;

public interface UserService {

    void register(User user);

    User login(String user_name, String password);

    boolean isUserValid(String user_name, String password);

    boolean isUserExisted(String user_name);

    boolean isPasswordValid(String password);
}
