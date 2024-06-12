package com.course.testng;

import org.testng.annotations.Test;

public class DependTest {

    //依赖测试:test2依赖test1；test1失败，test2不会执行
    @Test
    public void test1(){
        System.out.print("test1 run");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.print("test2 run");
    }
}
