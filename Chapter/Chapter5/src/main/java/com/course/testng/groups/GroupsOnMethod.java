package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.print("这是server组的测试方法1");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.print("这是server组的测试方法2");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.print("这是client组的测试方法3");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.print("这是client组的测试方法4");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.print("这是server组运行之前运行的方法");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer(){
        System.out.print("这是server组运行之后运行的方法");
    }

}
