package com.atms.config.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title:Session处理器
 * Description:修复 url:test;JSESSIONID=XXXXXXXXXXX 形式url会话丢失问题
 *
 * @author:eric
 * @date: 2016-1-7 16:22
 */
public class SessionHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        int index = target.lastIndexOf(";JSESSIONID");
        target = index == -1 ? target : target.substring(0, index);
        nextHandler.handle(target, request, response, isHandled);
    }
}
