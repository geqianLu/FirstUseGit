package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTrue",description = "测试准备工作，获取HttpClient对象")
    public void beforeTest(){
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSER);

        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }

    @Test(groups = "loginTrue",description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
        //从数据库查询case
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //发送请求
        String result = getResult(loginCase);
        //断言
        Assert.assertEquals(loginCase.getExpected(),result);

    }



    @Test(groups = "loginFalse",description = "用户登录失败接口测试")
    public void loginFalse() throws IOException {
        //从数据库查询case
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //发送请求
        String result = getResult(loginCase);
        //断言
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());

        //设置请求头
        post.setHeader("content-type","application/json");

        //设置请求body参数
        StringEntity entity = new StringEntity(param.toString(),"UTF-8");
        post.setEntity(entity);

        //发送请求
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");

        //存储cookies
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();

        return result;
    }
}
