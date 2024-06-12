package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public void stu1(){
        System.out.print("GroupsOnClass2 中的stu1运行啦！！！");
    }

    public void stu2(){
        System.out.print("GroupsOnClass2 中的stu2运行啦！！！");
    }
}
