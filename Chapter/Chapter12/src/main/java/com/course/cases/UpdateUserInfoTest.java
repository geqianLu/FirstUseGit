package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息测试")
    public void updateUserInfo() throws IOException, InterruptedException {
        //从数据库查询case
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求
        int result = getResult(updateUserInfoCase);
        //等待3秒
        Thread.sleep(3000);
        //查询数据库
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        //断言
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息测试")
    public void deleteUser() throws IOException, InterruptedException {
        //从数据库查询case
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求
        int result = getResult(updateUserInfoCase);
        //等待3秒
        Thread.sleep(3000);
        //查询数据库
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        //断言
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    @Test(dependsOnGroups = "loginTrue",description = "恢复刚刚update的用户信息")
    public void recoverUser() throws IOException, InterruptedException {
        //先从数据库查询刚刚update的的userId
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase",1);
        Integer userId = updateUserInfoCase.getUserId();

        //再new一个新的updateUserInfoCase
        UpdateUserInfoCase updateUserInfoCaseNew = new UpdateUserInfoCase();
        updateUserInfoCaseNew.setUserId(userId);
        updateUserInfoCaseNew.setUserName("lisi");
        System.out.println(updateUserInfoCaseNew.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求
        getResult(updateUserInfoCaseNew);
    }


    @Test(dependsOnGroups = "loginTrue",description = "删除刚刚添加的用户信息")
    public void deleteAddUser() throws IOException{
        //先从数据库查询刚刚添加的userName
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase= sqlSession.selectOne("addUserCase",1);
        String userName = addUserCase.getUserName();
        Integer userId = sqlSession.selectOne("getUserId",userName);
        //再从数据库查询添加的userId
        UpdateUserInfoCase updateUserInfoCase = new UpdateUserInfoCase();
        updateUserInfoCase.setUserId(userId);
        updateUserInfoCase.setIsDelete("1");
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求
        getResult(updateUserInfoCase);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());

        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"UTF-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");

        return Integer.parseInt(result);
    }
}
