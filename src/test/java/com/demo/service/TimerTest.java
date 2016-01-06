package com.demo.service;

import junit.framework.TestCase;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-16 13:52
 */
public class TimerTest extends TestCase {
    private Logger logger = Logger.getLogger(getClass());

    public void testTest1() throws Exception {
        timerTest();
    }


    private void timerTest() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info(System.currentTimeMillis());
            }
        }, 1000, 2000);

        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
