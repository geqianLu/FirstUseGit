package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public void stu3(){
        System.out.print("GroupsOnClass2 中的stu3运行啦！！！");
    }

    public void stu4(){
        System.out.print("GroupsOnClass2 中的stu4运行啦！！！");
    }
}
