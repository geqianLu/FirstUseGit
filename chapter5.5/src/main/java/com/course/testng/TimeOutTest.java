package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 1000)//单位为毫秒
    public void testSuccess() throws InterruptedException {
        Thread.sleep(500);
        System.out.print("执行成功！！！");
    }

    @Test(timeOut = 500)//单位为毫秒
    public void testFailed() throws InterruptedException {
        System.out.print("执行失败！！！");
        Thread.sleep(1000);
    }
}
