
package com.thymeleaf.dao;

import com.thymeleaf.entity.User;



public interface UserDao {
    User findByUserName(String user_name);
    void save(User user);

}
