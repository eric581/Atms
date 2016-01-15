package com.atms.app.system.controller;

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

    private Logger logger = Logger.getLogger(LoginController.class);

    public void index() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            redirect("/");
        } else {
            render("/views/login/login.jsp");
        }
    }

    public void go() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(getPara("username"), getPara("password")));
            if (subject.isAuthenticated() || subject.isRemembered()) {
                redirect("/");
                return;
            }
            redirect("/login");
        } catch (Exception e) {
            logger.error("密码验证出现异常:" + e.getMessage());
            redirect("/login");
        }
    }

    public void out() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        redirect("/login");
    }

}
