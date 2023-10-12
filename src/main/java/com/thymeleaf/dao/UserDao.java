package com.thymeleaf.dao;

import com.thymeleaf.entity.User;
import org.springframework.stereotype.Service;



public interface UserDao {
    User findByUserName(String user_name);
    void save(User user);

}
