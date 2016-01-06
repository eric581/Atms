package com.atms.controller;

import com.jfinal.core.Controller;
import org.apache.log4j.Logger;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-11 13:27
 */
public class LoginController extends Controller {

    private Logger logger = Logger.getLogger(BlogController.class);

    public void index() {
        render("login.jsp");
    }

    public void success(){
        render("/");
    }

}
