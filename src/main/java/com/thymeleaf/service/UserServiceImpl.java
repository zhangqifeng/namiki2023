package com.thymeleaf.service;

import com.thymeleaf.dao.UserDao;
import com.thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isUserValid(String user_name, String password) {
        User user=userDao.findByUserName(user_name);
        return user!=null&&user.getPassword().equals(password);
    }

    @Override
    public boolean isUserExisted(String user_Name) {
        User userDB = userDao.findByUserName(user_Name);
        return userDB!=null;
    }

    @Override
    public boolean isPasswordValid(String password) {
        String regex = "(?=.*[A-Za-z])(?=.*\\d).{6,15}$";
        return password.matches(regex);
    }

    @Override

    public void register(User user) {
        userDao.save(user);


    }


}
