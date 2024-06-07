package com.course.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

    @Test
    @Parameters({"name","age"})
    public void parameterTest(String name,String age){
        System.out.print("name: "+name+"; age: "+age);
    }
}
