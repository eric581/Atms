package com.atms.config;

import com.atms.app.user.entity.User;
import com.atms.config.handler.SessionHandler;
import com.atms.config.routes.*;
import com.atms.common.shiro.jar.core.ShiroInterceptor;
import com.atms.common.shiro.jar.core.ShiroPlugin;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
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
    @Override
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用getProperty(...)获取值
        PropKit.use("config.properties");
        me.setViewType(ViewType.JSP);//配置JSP视图
    }

    /**
     * 配置路由
     */
    @Override
    public void configRoute(Routes me) {
        this.routes = me;
        me.add(new MyRoutes());
    }

    /**
     * 配置插件
     */
    @Override
    public void configPlugin(Plugins me) {
        //添加redis插件
        me.add(new RedisPlugin("test", PropKit.get("redis.url"), PropKit.get("redis.password")));
        logger.info("redis插件加载成功");

        // 添加shiro插件
        me.add(new ShiroPlugin(routes));
        logger.info("shiro插件加载成功");

        //添加缓存插件
        me.add(new EhCachePlugin());
        logger.info("ehcache插件加载成功");

        //添加mysql插件
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbc-mysql-url"), PropKit.get("jdbc-mysql-user"), PropKit.get("jdbc-mysql-password"));
        me.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        me.add(arp);
        arp.addMapping("user", User.class);    // 映射blog 表到 Blog模型
        logger.info("druid插件加载成功");

    }

    /**
     * 配置全局拦截器
     */
    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new ShiroInterceptor());
    }

    /**
     * 配置处理器
     */
    @Override
    public void configHandler(Handlers me) {
        me.add(new SessionHandler());
    }


}
