package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    //超时测试
    @Test(timeOut = 1000)//单位为毫秒
    public void testSuccess() throws InterruptedException {
        Thread.sleep(500);
    }

    @Test(timeOut = 500)
    public void testFailed() throws InterruptedException {
        Thread.sleep(1000);
    }
}
