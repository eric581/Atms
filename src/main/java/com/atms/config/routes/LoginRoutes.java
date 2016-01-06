package com.demo.config.routes;

import com.demo.controller.LoginController;
import com.jfinal.config.Routes;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-11 13:33
 */
public class LoginRoutes extends Routes {
    @Override
    public void config() {
        add("/views/login", LoginController.class);
    }
}
