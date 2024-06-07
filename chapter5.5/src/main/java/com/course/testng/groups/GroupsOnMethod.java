package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.print("这是server组的测试方法1111");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.print("这是server组的测试方法2222");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.print("这是client组的测试方法3333");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.print("这是client组的测试方法4444");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.print("这是server组运行前需要运行的方法");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer(){
        System.out.print("这是server组运行后需要运行的方法！！！");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient(){
        System.out.print("这是client组运行前需要运行的方法");
    }

    @AfterGroups("client")
    public void afterGroupsOnClient(){
        System.out.print("这是clinet组运行后需要运行的方法！！！");
    }
}
