package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException, InterruptedException {
        //从数据库查询case
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        //发送请求
        JSONArray resultJson = getResult(getUserInfoCase);
        //等待3秒
        Thread.sleep(3000);
        //查询数据库
        User user = sqlSession.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);//从数据库查到期望值作为sql语句的id
        List userList = new ArrayList();
        userList.add(user);
        JSONArray expectedJsonArray = new JSONArray(userList);
        //断言
        JSONArray actualJsonArray = new JSONArray(resultJson.getString(0));//请求结果是个数组，需要取出来

        Assert.assertEquals(expectedJsonArray.toString(),actualJsonArray.toString());
    }

    private JSONArray getResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",getUserInfoCase.getUserId());

        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"UTF-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");

        JSONArray resultArray = new JSONArray(Arrays.asList(result));
        return resultArray;
    }
}
