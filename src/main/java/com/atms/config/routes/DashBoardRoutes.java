package com.atms.config.routes;

import com.atms.controller.CommonController;
import com.atms.controller.DashboardController;
import com.jfinal.config.Routes;

/**
 * Created by eric
 * 2014/9/17 19:28
 * nande52416@gmail.com
 */
public class DashBoardRoutes extends Routes {
    @Override
    public void config() {
        add("/", DashboardController.class);
    }
}
