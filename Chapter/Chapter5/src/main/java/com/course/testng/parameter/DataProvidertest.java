package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import java.lang.reflect.Method;

public class DataProvidertest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.print("name: "+name+"; age: "+age);
    }

    @DataProvider(name ="data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}
        };
        return o;
    }




    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.print("test1  name: "+name+"; age: "+age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.print("test2  name: "+name+"; age: "+age);
    }

    @DataProvider(name ="methodData")
    public Object[][] providerData1(Method method){
        Object[][] o1 = null;
        if (method.getName().equals("test1")){
            o1 = new Object[][]{
                    {"zhangsan",10},
                    {"lisi",20}
            };

        }else if(method.getName().equals("test2")){
            o1 = new Object[][]{
                {"wangwu",30},
                {"zhaoliu",40}
             };
        };
        return o1;
    }

}
