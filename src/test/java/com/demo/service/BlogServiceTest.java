package com.demo.service;

import com.jfinal.aop.Enhancer;
import junit.framework.TestCase;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-15 13:50
 */
public class BlogServiceTest extends TestCase {

    public static final BlogService blogService = Enhancer.enhance(BlogService.class);

    public void testTest1() throws Exception {
        blogService.test();
    }
}