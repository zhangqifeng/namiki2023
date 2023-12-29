package com.thymeleaf.service;

import com.thymeleaf.entity.User;

public interface UserService {
    void register(User user);

    boolean isUserValid(String userName, String password);

    boolean isUserExisted(String userName);

    boolean isPasswordValid(String password);

}
