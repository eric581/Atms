package com.demo;

import com.jfinal.core.JFinal;

/**
 * @author eric 597918533 15-12-9 03:59 JDK 1.7
 * @Title: [title]
 * @Description: [description]
 * @Project: jfinal
 * @Name: Run
 */
public class Run {
    /**
     * 运行此 main 方法可以启动项目
     */
    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8081, "/", 5);
    }
}
