package com.demo.service.shiro.jar.core;

import com.demo.service.shiro.jar.core.handler.AuthzHandler;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-16 14:47
 */
public class ShiroInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(ShiroInterceptor.class);

    public void intercept(Invocation ai) {
        //路径权限 //注解权限
        List<AuthzHandler> ahs = ShiroKit.getAuthzHandler(ai.getController().getRequest(), ai.getActionKey());
        //权限验证
        if (assertNoAuthorized(ai, ahs)) return;
        // 执行正常逻辑
        ai.invoke();
    }

    /**
     * 权限检查
     */
    private boolean assertNoAuthorized(Invocation ai, List<AuthzHandler> ahs) {
        // 存在访问控制处理器。
        if (ahs != null && ahs.size() > 0) {
            // 登录前访问页面缓存
            if (!SubjectKit.isAuthed()) {
                WebUtils.saveRequest(ai.getController().getRequest());
            }
            //rememberMe自动登录
            Subject subject = SubjectKit.getSubject();
            if (!subject.isAuthenticated() && subject.isRemembered()) {
                Object principal = subject.getPrincipal();
                if (principal == null) {
                    SubjectKit.getSubject().logout();
                }
            }

            try {
                // 执行权限检查。
                for (AuthzHandler ah : ahs) {
                    ah.assertAuthorized();
                }
            } catch (UnauthenticatedException lae) {
                // RequiresGuest，RequiresAuthentication，RequiresUser，未满足时，抛出未经授权的异常。
                // 如果没有进行身份验证，返回HTTP401状态码
                ai.getController().renderError(401);
                return true;
            } catch (AuthorizationException ae) {
                // RequiresRoles，RequiresPermissions授权异常
                // 如果没有权限访问对应的资源，返回HTTP状态码403。
                ai.getController().renderError(403);
                return true;
            } catch (Exception e) {
                // 出现了异常，应该是没有登录。
                ai.getController().renderError(401);
                return true;
            }
        }
        return false;
    }
}