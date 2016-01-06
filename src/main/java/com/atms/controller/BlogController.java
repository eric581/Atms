package com.atms.controller;

import com.atms.entity.Blog;
import com.atms.config.interceptor.BlogInterceptor;
import com.atms.config.validator.BlogValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;
import org.apache.log4j.Logger;


/**
 * BlogController
 * 所有 sql 写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {

    private Logger logger = Logger.getLogger(BlogController.class);

    public void index() {
        render("index.html");
    }

    public void add() {
    }

    @Before(BlogValidator.class)
    public void save() {
        getModel(Blog.class).save();
        redirect("/blog");
    }

    public void edit() {
        setAttr("blog", Blog.dao.findById(getParaToInt()));
    }

    @Before(BlogValidator.class)
    public void update() {
        getModel(Blog.class).update();
        redirect("/blog");
    }

    public void delete() {
        Blog.dao.deleteById(getParaToInt());
        redirect("/blog");
    }

    public void test() {
        logger.info("test");
        Redis.use("test").set("name", "eric2");
        redirect("/blog");
    }
}


