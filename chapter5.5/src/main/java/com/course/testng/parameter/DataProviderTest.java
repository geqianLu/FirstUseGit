package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data1")
    public void testDataprovider(String name,int age){
        System.out.print("name="+name+"; age="+age);
    }

    @DataProvider(name="data1")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zhangsan01",10},
                {"lisi01",15}
        };
        return o;
    }


    @Test(dataProvider = "data2")
    public void dataTest1(String name,int age){
        System.out.print("name="+name+"; age="+age);
    }

    @Test(dataProvider = "data2")
    public void dataTest2(String name,int age){
        System.out.print("name="+name+"; age="+age);
    }

    @DataProvider(name="data2")
    public Object[][] providerData(Method method){
        Object[][] o2 = null;
        if (method.getName().equals("dataTest1")) {
            o2 = new Object[][]{
                    {"zhangsan",10},
                    {"lisi",15}
            };
        }else if(method.getName().equals("dataTest2")){
            o2 = new Object[][]{
                    {"wangwu",20},
                    {"zhuoliu",25}
            };
        };
        return o2;
    }



}
