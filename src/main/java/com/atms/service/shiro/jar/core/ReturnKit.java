package com.atms.service.shiro.jar.core;

import com.jfinal.core.Controller;
import com.jfinal.render.JsonRender;
import com.jfinal.render.Render;

import javax.servlet.http.HttpServletRequest;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-16 15:49
 */
public class ReturnKit {
    private static String dataTypeName = "returnType";

    public static String getDataTypeName() {
        return dataTypeName;
    }

    public static void setDataTypeName(String dataTypeＮame) {
        dataTypeName = dataTypeＮame;
    }

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    public static boolean isJson(HttpServletRequest request, String dataTypeName) {
        return ((isAjax(request)) && (!"default".equalsIgnoreCase(request.getParameter(dataTypeName)))) || ("json".equalsIgnoreCase(request.getParameter(dataTypeName)));
    }

    public static boolean isJson(HttpServletRequest request) {
        return isJson(request, dataTypeName);
    }

    public static boolean isJson(Controller controller) {
        return controller.getRender() instanceof JsonRender;
    }

    public static boolean isJson(Render render) {
        return render instanceof JsonRender;
    }

    public static enum ReturnType {
        DFAULT(0), JSON(1);

        private final int value;

        private ReturnType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
