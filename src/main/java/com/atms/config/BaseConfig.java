package com.atms.config;

import com.atms.config.handler.SessionHandler;
import com.atms.config.routes.*;
import com.atms.service.shiro.jar.core.ShiroInterceptor;
import com.atms.service.shiro.jar.core.ShiroPlugin;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import org.apache.log4j.Logger;


/**
 * API引导式配置
 */
public class BaseConfig extends JFinalConfig {

    private Routes routes;

    Logger logger = Logger.getLogger(getClass());

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用getProperty(...)获取值
        loadPropertyFile("config.properties");
        me.setViewType(ViewType.JSP);//配置JSP视图
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        this.routes = me;
        me.add(new BlogRoutes());
        me.add(new DashBoardRoutes());
        me.add(new MailRoutes());
        me.add(new LoginRoutes());
        me.add(new SearchRoutes());
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        me.add(new RedisPlugin("test", getProperty("redis.url"), getProperty("redis.password")));
        logger.info("redis插件加载成功");

        // 添加shiro插件
        me.add(new ShiroPlugin(routes));
        logger.info("shiro插件加载成功");

        //添加缓存插件
        me.add(new EhCachePlugin());
        logger.info("ehcache插件加载成功");

    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
        me.add(new ShiroInterceptor());
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new SessionHandler());
    }


}
