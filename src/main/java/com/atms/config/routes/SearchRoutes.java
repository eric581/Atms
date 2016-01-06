package com.atms.config.routes;

import com.atms.controller.LoginController;
import com.atms.controller.SearchController;
import com.jfinal.config.Routes;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-11 13:33
 */
public class SearchRoutes extends Routes {
    @Override
    public void config() {
        add("/search", SearchController.class);
    }
}