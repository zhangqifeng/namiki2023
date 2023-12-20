package com.thymeleaf.service;

import com.thymeleaf.entity.User;

public interface UserService {
    void register(User user);

    boolean isUserValid(String userName, String password);

    boolean isUserExisted(String userName);
<<<<<<< HEAD
    boolean isPasswordValid(String password);
=======
>>>>>>> a3c84c173a29ca58163a12bbd59cf10875ea08cf
}
