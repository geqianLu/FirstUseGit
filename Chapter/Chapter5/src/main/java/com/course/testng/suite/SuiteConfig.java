package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.print("before suite运行啦");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.print("after suite 运行啦");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.print("before test 运行啦");
    }

    @AfterTest
    public void afterTest(){
        System.out.print("after test 运行啦");
    }

}
