package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {

    @Test
    public void test1(){
        System.out.println("test1 用例执行！！!");
        System.out.printf("test1 用例 Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.println("test2 用例执行！！!");
        System.out.printf("test2 用例 Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.println("test3 用例执行！！!");
        System.out.printf("test3 用例 Thread Id : %s%n",Thread.currentThread().getId());
    }

}
