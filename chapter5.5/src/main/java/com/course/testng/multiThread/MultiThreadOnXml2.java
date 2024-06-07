package com.course.testng.multiThread;

import org.testng.annotations.Test;

import static java.lang.Thread.currentThread;

public class MultiThreadOnXml2 {

    @Test
    public void test1(){
        System.out.printf("MultiThreadXml2222 Thread Id : %s%n", currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("MultiThreadXml2222 Thread Id : %s%n", currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("MultiThreadXml2222 Thread Id : %s%n", currentThread().getId());
    }
}
