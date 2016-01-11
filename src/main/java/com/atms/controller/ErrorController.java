package com.atms.controller;

import com.jfinal.core.Controller;

/**
 * ErrorController
 */
public class ErrorController extends Controller {

    public void index() {
        render("/index.jsp");
    }

    public void error404() {
        render("/views/error/404.jsp");
    }

    public void error500() {
        render("/views/error/500.jsp");
    }
}
