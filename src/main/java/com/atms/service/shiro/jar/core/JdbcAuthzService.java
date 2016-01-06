package com.demo.service.shiro.jar.core;


import com.demo.service.shiro.jar.core.handler.AuthzHandler;

import java.util.Map;

public interface JdbcAuthzService {
  public Map<String, AuthzHandler> getJdbcAuthz();
}
