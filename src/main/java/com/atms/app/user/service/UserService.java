package com.atms.app.user.service;


import com.atms.app.user.entity.User;
import org.apache.log4j.Logger;

public class UserService {

    private Logger logger = Logger.getLogger(UserService.class);

    public User getUserById(String id) {
        return User.dao.findById(id);
    }

}
