package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotation {

    @Test(invocationCount = 6,threadPoolSize = 3)
    public void test(){
        System.out.println("test用例执行！！!");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
}
