package com.atms.app.user.controller;

import com.atms.app.user.entity.User;
import com.atms.app.user.service.UserService;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import org.apache.log4j.Logger;

/**
 * UserController
 */
public class UserController extends Controller {

    private Logger logger = Logger.getLogger(UserController.class);

    public static final UserService userService = Enhancer.enhance(UserService.class);


    public void index() {

        User user = userService.getUserById("1");

        logger.info("user:" + user);
        render("/index.jsp");
    }

}
