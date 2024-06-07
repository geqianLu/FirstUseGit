package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {

    /**什么时候会用电异常测试？
     * 在我们的期望结果是某一个异常的时候
     * 比如：我们传入了不合法的参数，程序抛出了异常
     * 也就是我们的预期结果就是这个异常
     */

    //这是一个测试结果会失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.print("这是一个失败的异常测试");
    }

    //这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.print("这是一个成功的异常测试");
        throw new RuntimeException();
    }

}
