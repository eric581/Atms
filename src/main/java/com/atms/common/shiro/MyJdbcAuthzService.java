package com.atms.common.shiro;


import com.atms.common.shiro.jar.core.JdbcAuthzService;
import com.atms.common.shiro.jar.core.handler.AuthzHandler;

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