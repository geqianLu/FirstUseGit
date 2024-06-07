package com.course.testng.suite;

import org.testng.annotations.*;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.print("BeforeSuite运行啦！！！");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.print("AfterSuite运行啦！！！");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.print("BeforeTest运行啦！！！");
    }

    @AfterTest
    public void afterTest(){
        System.out.print("AfterTest运行啦！！！");
    }
}
