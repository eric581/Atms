package com.atms.service.shiro.jar.core;


import com.atms.service.shiro.jar.core.handler.AuthzHandler;

import java.util.Map;

public interface JdbcAuthzService {
  public Map<String, AuthzHandler> getJdbcAuthz();
}
