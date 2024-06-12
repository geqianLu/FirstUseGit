package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {

    //忽略测试
    @Test(enabled = false)
    public void ignore1(){
        System.out.print("ignore1 运行啦！！！");
    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.print("ignore2 运行啦！！！");
    }

    @Test(enabled = true)
    public void ignore3(){
        System.out.print("ignore3 运行啦！！！");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
}
