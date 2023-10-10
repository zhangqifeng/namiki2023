package com.thymeleaf.service;

import com.thymeleaf.dao.UserDao;
import com.thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
     User userDB = userDao.findByUserName(user.getUser_name());
     if (!ObjectUtils.isEmpty(userDB)) throw new RuntimeException("当前用户名已被注册");
     userDao.save(user);


    }
}
