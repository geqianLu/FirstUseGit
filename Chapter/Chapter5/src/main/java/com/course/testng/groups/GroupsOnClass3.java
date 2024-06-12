package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {

    public void teacher1(){
        System.out.print("GroupsOnClass3 中的teacher1运行啦！！！");
    }

    public void teacher2(){
        System.out.print("GroupsOnClass3 中的teacher2运行啦！！！");
    }
}
