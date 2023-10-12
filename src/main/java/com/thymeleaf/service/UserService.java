package com.thymeleaf.service;

import com.thymeleaf.entity.User;

public interface UserService {
    void register(User user);

    User login(String user_name, String password);
}
