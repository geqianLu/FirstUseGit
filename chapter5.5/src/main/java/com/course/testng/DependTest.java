package com.course.testng;

import org.testng.annotations.Test;

public class DependTest {

    /** 依赖测试
     * case4依赖的case3，case3失败后，case4会被ignore
     */

    @Test
    public void test1(){
        System.out.print("test1 run");
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.print("test2 run");
    }

    @Test
    public void test3(){
        System.out.print("test3 run");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = {"test3"})
    public void test4(){
        System.out.print("test4 run");
    }
}
