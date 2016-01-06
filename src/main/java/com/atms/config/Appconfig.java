//package com.demo.config;
//
//
//import cn.dreampie.shiro.core.ShiroInterceptor;
//import cn.dreampie.shiro.core.ShiroPlugin;
//import com.jfinal.config.Constants;
//import com.jfinal.config.Handlers;
//import com.jfinal.config.Interceptors;
//import com.jfinal.config.JFinalConfig;
//import com.jfinal.config.Plugins;
//import com.jfinal.config.Routes;
//import com.jfinal.ext.handler.ContextPathHandler;
//import com.jfinal.ext.interceptor.SessionInViewInterceptor;
//import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
//import com.jfinal.plugin.druid.DruidPlugin;
//import com.jfinal.plugin.druid.DruidStatViewHandler;
//import com.jfinal.plugin.ehcache.EhCachePlugin;
//import com.jfinal.render.FreeMarkerRender;
//
///**
// * JFinal项目全局配置
// */
//public class AppConfig extends JFinalConfig {
//
//    Routes routes;
//
//    /**
//     * 配置常量
//     */
//    public void configConstant(Constants me) {
//        // 加载少量必要配置，随后可用getProperty(...)获取值
//        loadPropertyFile("application.properties");
//        me.setDevMode(getPropertyToBoolean("devMode", false));
//        me.setBaseViewPath("/WEB-INF/views/");
//        me.setError500View("/WEB-INF/views/error/500.html");
//        me.setError404View("/WEB-INF/views/error/404.html");
//        FreeMarkerRender.setProperty("template_update_delay", "0");// 模板更更新时间,0表示每次都加载
//        FreeMarkerRender.setProperty("classic_compatible", "true");// 如果为null则转为空值,不需要再用!处理
//        FreeMarkerRender.setProperty("whitespace_stripping", "true");// 去除首尾多余空格
//        FreeMarkerRender
//                .setProperty("auto_import", "/WEB-INF/ui/dwz.ftl as dwz");
//        FreeMarkerRender.setProperty("date_format", "yyyy-MM-dd");
//        FreeMarkerRender.setProperty("time_format", "HH:mm:ss");
//        FreeMarkerRender.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
//        FreeMarkerRender.setProperty("default_encoding", "UTF-8");
//    }
//
//    /**
//     * 配置路由
//     */
//    public void configRoute(Routes me) {
//        this.routes = me;
//        me.add("/management/security/cacheManage", CacheManageController.class);
//        me.add("/management/security/module", ModuleController.class);
//        me.add("/management/security/organization", OrganizationController.class);
//        me.add("/management/security/organizationRole", OrganizationRoleController.class);
//        me.add("/management/security/role", RoleController.class);
//        me.add("/management/security/user", UserController.class);
//        me.add("/management/security/userRole", UserRoleController.class);
//    }
//
//    /**
//     * 配置插件
//     */
//    public void configPlugin(Plugins me) {
//        DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbc.url"),
//                getProperty("jdbc.username"), getProperty("jdbc.password")
//                .trim());
//        druidPlugin.setInitialSize(getPropertyToInt("druid.initialSize"));
//        druidPlugin.setMaxActive(getPropertyToInt("druid.maxActive"));
//        druidPlugin.setMaxWait(getPropertyToInt("druid.maxWait"));
//        druidPlugin.setMinIdle(getPropertyToInt("druid.minIdle"));
//        me.add(druidPlugin);
//
//        // 配置ActiveRecord插件
//        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
//        me.add(arp);
//        arp.setShowSql(true);
//        //添加表映射
//        arp.addMapping("security_module", Module.class);
//        arp.addMapping("security_organization", Organization.class);
//        arp.addMapping("security_organization_role", OrganizationRole.class);
//        arp.addMapping("security_permission", Permission.class);
//        arp.addMapping("security_role", Role.class);
//        arp.addMapping("security_role_permission", RolePermission.class);
//        arp.addMapping("security_user", User.class);
//        arp.addMapping("security_user_role", UserRole.class);
//
//        // 添加自动绑定model与表插件
//        // AutoTableBindPlugin atbp = new
//        // AutoTableBindPlugin(druidPlugin,SimpleNameStyles.LOWER_UNDERLINE);
//        // me.add(atbp);
//
//        // 添加使用XML管理SQL插件
//        me.add(new SqlInXmlPlugin());
//
//        // 添加shiro插件
//        ShiroPlugin shiroPlugin = new ShiroPlugin(routes);
//        me.add(shiroPlugin);
//
//        //添加缓存插件
//        me.add(new EhCachePlugin());
//    }
//
//    /**
//     * 配置全局拦截器
//     */
//    public void configInterceptor(Interceptors me) {
//        //在VIEW中可以使用SESSION
//        me.add(new SessionInViewInterceptor());
//
//        me.add(new ShiroInterceptor());
//    }
//
//    /**
//     * 配置处理器
//     */
//    public void configHandler(Handlers me) {
//        // 添加druid连接池
//        me.add(new DruidStatViewHandler("/druid"));
//        // 添加页面contextpath
//        me.add(new ContextPathHandler());
//
//
//    }
//}
