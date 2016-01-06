package com.atms.service.shiro;

import com.atms.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDbRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory
            .getLogger(ShiroDbRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        System.out.println("进入查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if (username != null && !"".equals(username)) {
            User user = new User();
            user.set("username","eric");
            user.set("password","eric");
            return new SimpleAuthenticationInfo(
                    user.get("username"), user.get("password"), getName());
        }
        return null;
    }
}
