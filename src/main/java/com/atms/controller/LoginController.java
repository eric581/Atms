package com.atms.controller;

import com.jfinal.core.Controller;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

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
        render("/views/login/login.jsp");
    }

    public void go() {
        SecurityUtils.getSubject().login(new UsernamePasswordToken(getPara("username"), getPara("password")));

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            redirect("/");
            return;
        }
        redirect("/login");
    }

    public void out() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        redirect("/login");
    }

}
