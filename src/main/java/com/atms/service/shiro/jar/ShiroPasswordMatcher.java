package com.atms.service.shiro.jar;

import com.atms.service.shiro.jar.hasher.Hasher;
import com.jfinal.plugin.activerecord.Model;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroPasswordMatcher extends PasswordMatcher {
    private static final Logger logger = LoggerFactory.getLogger(ShiroPasswordMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        String infoCredentials = String.valueOf(getStoredPassword(info));
//        String tokenCredentials = Md5Encoding(String.valueOf(getSubmittedPassword(token)), ((UsernamePasswordToken) token).getUsername());
//        logger.debug("username:" + ((UsernamePasswordToken) token).getUsername() + ",password:" + getSubmittedPassword(token) + " - " + infoCredentials + " valid " + tokenCredentials);
//        return infoCredentials.equals(tokenCredentials);
        boolean match = false;
        String hasher = ((Model<?>) info.getPrincipals().getPrimaryPrincipal()).get("hasher");

        String default_hasher = Hasher.DEFAULT.value();
        if (default_hasher.equals(hasher)) {
            match = super.doCredentialsMatch(token, info);
        }
        return match;
    }

    @Override
    protected Object getSubmittedPassword(AuthenticationToken token) {
        Object submit = super.getSubmittedPassword(token);
        if (submit instanceof char[]) {
            submit = String.valueOf((char[]) submit);
        }
        return submit;
    }

    @Override
    protected Object getStoredPassword(AuthenticationInfo storedUserInfo) {
        Object stored = super.getStoredPassword(storedUserInfo);

        if (stored instanceof char[]) {
            stored = String.valueOf((char[]) stored);
        }
        return stored;
    }
}