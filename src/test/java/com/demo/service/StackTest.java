package com.demo.service;

import com.google.common.collect.Maps;
import junit.framework.TestCase;
import org.apache.hadoop.hdfs.util.EnumCounters;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-30 11:30
 */
public class StackTest extends TestCase {
    private Logger logger = Logger.getLogger(getClass());

    Map<String, Integer> result = Maps.newHashMap();
    int i = 0;

    public void testTest1() throws Exception {
        stackTest();
    }

    private void stackTest() {
        recursion();
    }

    private void recursion() {
        try {
            result.put("key", i++);
            recursion();
        } catch (Throwable t) {
            logger.error("stack is:" + i);
            try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
