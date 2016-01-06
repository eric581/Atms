package com.demo.config.routes;

import com.demo.controller.CommonController;
import com.demo.controller.DashboardController;
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
