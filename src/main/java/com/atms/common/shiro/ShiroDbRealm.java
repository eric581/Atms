package com.atms.common.shiro;

import com.atms.app.user.entity.User;
import com.atms.app.user.service.UserService;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Record;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ShiroDbRealm extends AuthorizingRealm {

    private static final Logger logger = Logger.getLogger(ShiroDbRealm.class);

    public static final UserService userService = Enhancer.enhance(UserService.class);


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        logger.debug("shiro开始授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(User.dao.findRightsByUserId(""));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        logger.debug("shiro开始登录认证");

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        Record record = User.dao.findByUserName(username);
        if (record != null && record.get("password").equals(password)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }
        throw new AuthenticationException("密码验证不通过！");
    }
}
