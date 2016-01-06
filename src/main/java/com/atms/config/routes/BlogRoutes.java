package com.atms.config.routes;

import com.atms.controller.BlogController;
import com.jfinal.config.Routes;

/**
 * Created by eric
 * 2014/9/17 19:27
 * nande52416@gmail.com
 */
public class BlogRoutes extends Routes {
    @Override
    public void config() {
        add("/blog", BlogController.class);
    }
}
