package com.atms.service.shiro.jar.core.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;


public class JdbcPermissionAuthzHandler extends AbstractAuthzHandler {
    private final String jdbcPermission;

    public JdbcPermissionAuthzHandler(String jdbcPermission) {
        this.jdbcPermission = jdbcPermission;
    }

    public void assertAuthorized() throws AuthorizationException {
        Subject subject = getSubject();
        //数据库权限
        if (jdbcPermission != null) {
            subject.checkPermission(jdbcPermission);
        }
    }
}
