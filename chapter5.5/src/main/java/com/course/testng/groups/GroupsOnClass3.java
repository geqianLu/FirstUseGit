package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {
    public void teacher1(){
        System.out.print("GroupsOnClass3中的teacher5555运行");
    }

    public void teacher2(){
        System.out.print("GroupsOnClass3中的teacher6666运行");
    }

}
