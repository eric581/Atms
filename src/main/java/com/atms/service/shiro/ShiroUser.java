package com.atms.service.shiro;

import java.io.Serializable;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-15 17:58
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1748602382963711884L;
    private Long id;
    private String loginName;

    public ShiroUser() {

    }

    public Long getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    @Override
    public String toString() {
        return loginName;
    }
}
