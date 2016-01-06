package com.demo.config.routes;

import com.demo.controller.MailController;
import com.jfinal.config.Routes;

/**
 * Created by eric
 * 2014/9/17 20:04
 * nande52416@gmail.com
 */
public class MailRoutes extends Routes {
    @Override
    public void config() {
        add("/mail", MailController.class);
    }
}
