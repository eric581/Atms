package com.demo.service;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-16 10:07
 */
public class ShiroTest extends TestCase {
    private Logger logger = Logger.getLogger(getClass());

    public void testTest1() throws Exception {
        testShiro();
    }



    private void testShiro() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        org.apache.shiro.mgt.SecurityManager securityManager
                = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject user = SecurityUtils.getSubject();
        logger.info("User is authenticated:  " + user.isAuthenticated());
    }
}
