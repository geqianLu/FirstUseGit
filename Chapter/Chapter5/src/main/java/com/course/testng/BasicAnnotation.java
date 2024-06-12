package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    //基本注解
    @Test
    public void testCase1(){
        System.out.print("这是第一条测试用例1");
    }

    @BeforeMethod
    public void beforeTestCase1(){
        System.out.print("这是第一条测试用例1开始前执行");
    }

    @AfterMethod
    public void AfterTestCase1(){
        System.out.print("这是第一条测试用例1执行后执行");
    }

    @BeforeClass
    public void beforeClass1(){
        System.out.print("这是类开始前执行");
    }

    @AfterClass
    public void afetrClass1(){
        System.out.print("这是类运行后执行");
    }

    @BeforeSuite
    public void beforeSuite1(){
        System.out.print("这是测试套件运行之前执行1");
    }

    @AfterSuite
    public void afterSuite1(){
        System.out.print("这是测试套件运行之后执行1");
    }
}
