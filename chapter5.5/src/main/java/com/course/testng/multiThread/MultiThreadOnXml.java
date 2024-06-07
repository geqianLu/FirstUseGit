package com.course.testng.multiThread;

import org.testng.annotations.Test;

import static java.lang.Thread.*;

public class MultiThreadOnXml {

    @Test
    public void test1(){
        System.out.printf("MultiThreadXml Thread Id : %s%n", currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("MultiThreadXml Thread Id : %s%n", currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("MultiThreadXml Thread Id : %s%n", currentThread().getId());
    }

}
