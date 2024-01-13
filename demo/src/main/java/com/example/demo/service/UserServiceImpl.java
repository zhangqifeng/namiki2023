package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
        User userDB = userDao.findByUserName(user.getUser_name());
//        if(!ObjectUtils.isEmpty(userDB)) throw new RuntimeException("当前用户名已被注册！");
        userDao.save(user);
    }

    @Override
    public User login(String user_name, String password) {
        User user = userDao.findByUserName(user_name);
        if (ObjectUtils.isEmpty(user))throw new RuntimeException("用户名不正确");
        if (!user.getPassword().equals(password))throw new RuntimeException("密码输入错误！");
        return user;
    }

    @Override
    public boolean isUserValid(String user_name, String password) {
        User user=userDao.findByUserName(user_name);
        return user!=null&&user.getPassword().equals(password);
    }

    @Override
    public boolean isUserExisted(String user_name) {
        User userDB = userDao.findByUserName(user_name);
        return userDB!=null;
    }

    @Override
    //正则表达式:用于检查一个字符串是否符合特定的格式规则
    //^:表示匹配字符串的开始
    //(?=.*[A-Za-z]):正向预查，要求字符串中至少包含一个字母 (大小写不限)
    // (?=.*\\d):再次正向预查，要求字符串中至少包含一个数字
    //[6,15]:匹配任意字符(除了换行符)至少6次，但不超过15次
    // $:表示匹配字符串的结束
    public boolean isPasswordValid(String password) {
        //在这里添加密码验证的逻辑，例如，检查密码是否符合要求
        // 返回true表示密码有效，返回false表示密码无效
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{6,15}$";
        return password.matches(regex);
    }
}
