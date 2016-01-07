package com.atms.config.routes;

import com.atms.controller.LoginController;
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
        add("/login", LoginController.class);
    }
}
