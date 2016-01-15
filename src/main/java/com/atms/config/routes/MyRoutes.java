package com.atms.config.routes;

import com.atms.app.search.controller.SearchController;
import com.atms.app.system.controller.DashboardController;
import com.atms.app.system.controller.LoginController;
import com.atms.app.user.controller.UserController;
import com.jfinal.config.Routes;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-11 13:33
 */
public class MyRoutes extends Routes {
    @Override
    public void config() {
        add("/search", SearchController.class);
        add("/login", LoginController.class);
        add("/", DashboardController.class);
        add("/user", UserController.class);
    }
}
