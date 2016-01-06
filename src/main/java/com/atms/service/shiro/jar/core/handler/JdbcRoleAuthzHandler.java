package com.demo.service.shiro.jar.core.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;


public class JdbcRoleAuthzHandler extends AbstractAuthzHandler {
  private final String jdbcRole;

  public JdbcRoleAuthzHandler(String jdbcRole) {
    this.jdbcRole = jdbcRole;
  }

  @Override
  public void assertAuthorized() throws AuthorizationException {
    Subject subject = getSubject();
    //数据库权限
    if (jdbcRole != null) {
      subject.checkRole(jdbcRole);
      return;
    }
  }
}
