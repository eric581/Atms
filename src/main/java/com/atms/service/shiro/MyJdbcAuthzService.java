package com.atms.service.shiro;


import com.atms.service.shiro.jar.core.JdbcAuthzService;
import com.atms.service.shiro.jar.core.handler.AuthzHandler;
import com.atms.service.shiro.jar.core.handler.JdbcPermissionAuthzHandler;

import java.util.*;

public class MyJdbcAuthzService implements JdbcAuthzService {
    public Map<String, AuthzHandler> getJdbcAuthz() {
        //加载数据库的url配置
        Map<String, AuthzHandler> authzJdbcMaps = new HashMap<String, AuthzHandler>();
        //遍历角色
//        authzJdbcMaps.put("/search", new JdbcPermissionAuthzHandler("search"));
        return authzJdbcMaps;
    }
}