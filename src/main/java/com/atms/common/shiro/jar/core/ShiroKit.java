package com.atms.common.shiro.jar.core;

import com.atms.common.shiro.jar.core.handler.AuthzHandler;
import com.atms.common.shiro.jar.core.handler.JdbcPermissionAuthzHandler;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * ShiroKit. (Singleton, ThreadSafe)
 *
 * @author dafei
 */
public class ShiroKit {

    private static final Logger log = LoggerFactory.getLogger(ShiroKit.class);
    /**
     * 用来记录那个action或者actionpath中是否有shiro认证注解。
     */
    private static ConcurrentMap<String, AuthzHandler> authzMaps = null;

    private static Map<String, AuthzHandler> authzJdbcMaps = null;

    public static AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * jdbc的权限加载器
     */
    private static JdbcAuthzService jdbcAuthzService;

    private static boolean and = false;

    /**
     * 禁止初始化
     */
    private ShiroKit() {
    }

    static void init(JdbcAuthzService jdbcAuthzSrc, ConcurrentMap<String, AuthzHandler> amaps, boolean isAnd) {
        jdbcAuthzService = jdbcAuthzSrc;
        authzMaps = amaps;
        and = isAnd;
        //加载数据库权限
        loadJdbcAuthz();
    }


    static AuthzHandler getAuthzHandler(String actionKey) {
        return authzMaps.get(actionKey);
    }

    static List<AuthzHandler> getJdbcAuthzHandler(HttpServletRequest request) {
        List<AuthzHandler> result = new ArrayList<AuthzHandler>();
        String url = WebUtils.getPathWithinApplication(request);
        for (String key : authzJdbcMaps.keySet()) {
            if (antPathMatcher.match(key, url)) {
                result.add(authzJdbcMaps.get(key));
                if (!and) break;
            }
        }
        return result;
    }

    static List<AuthzHandler> getAuthzHandler(HttpServletRequest request, String actionKey) {
        List<AuthzHandler> result = getJdbcAuthzHandler(request);
        AuthzHandler ah = getAuthzHandler(actionKey);
        if (ah != null) {
            result.add(ah);
        }
        return result;
    }

    /**
     * 判断是否已经存在一个相同的路径
     *
     * @param url url
     * @return boolean
     */
    public static boolean hasJdbcAuthz(String url) {
        return authzJdbcMaps.containsKey(url);
    }

    /**
     * jdbc 权限
     *
     * @param url   权限url规则
     * @param value 权限标识
     */
    public static void addJdbcAuthz(String url, String value) {
        authzJdbcMaps.put(url, new JdbcPermissionAuthzHandler(value));
    }

    /**
     * jdbc 取消某个权限
     *
     * @param url 权限url规则
     */
    public static void removeJdbcAuthz(String url) {
        authzJdbcMaps.remove(url);
    }

    /**
     * load  jdbc 权限
     */
    public static void loadJdbcAuthz() {
        loadJdbcAuthz(false);
    }

    /**
     * @param clear 清除原来的权限
     */
    public static void loadJdbcAuthz(boolean clear) {
        if (jdbcAuthzService != null) {
            if (clear) {
                authzJdbcMaps.clear();
            }
            authzJdbcMaps = jdbcAuthzService.getJdbcAuthz();
        } else
            log.error("authzJdbcService not found!can't load database url premission");
    }
}
