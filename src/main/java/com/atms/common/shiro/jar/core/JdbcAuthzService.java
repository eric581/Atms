package com.atms.common.shiro.jar.core;


import com.atms.common.shiro.jar.core.handler.AuthzHandler;

import java.util.Map;

public interface JdbcAuthzService {
    Map<String, AuthzHandler> getJdbcAuthz();
}
