package com.demo.controller;

import com.jfinal.core.Controller;
import org.apache.log4j.Logger;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-15 14:05
 */
public class DashboardController extends Controller {

    private Logger logger = Logger.getLogger(DashboardController.class);

    public void index() {
        render("/views/dashboard/index.jsp");
    }
}
