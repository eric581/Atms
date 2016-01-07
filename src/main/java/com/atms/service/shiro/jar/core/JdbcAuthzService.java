package com.atms.service.shiro.jar.core;


import com.atms.service.shiro.jar.core.handler.AuthzHandler;

import java.util.Map;

public interface JdbcAuthzService {
    Map<String, AuthzHandler> getJdbcAuthz();
}
