package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotation {

    //在方法上指定线程池
    //invocationCount：用例执行次数；threadPoolSize：线程池大小，即最大线程池数
    @Test(invocationCount = 4,threadPoolSize = 2)
    public void test1(){
        System.out.println("test1用例执行！！!");
        System.out.printf("test1 Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test(invocationCount = 4,threadPoolSize = 2)
    public void test2(){
        System.out.println("test2用例执行！！!");
        System.out.printf("test2 Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test(invocationCount = 4,threadPoolSize = 2)
    public void test3(){
        System.out.println("test3用例执行！！!");
        System.out.printf("test3 Thread Id : %s%n",Thread.currentThread().getId());
    }
}
