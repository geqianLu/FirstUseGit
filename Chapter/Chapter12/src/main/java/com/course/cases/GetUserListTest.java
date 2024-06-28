package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
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
import java.util.List;

public class GetUserListTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男性的用户信息")
    public void getUserList() throws IOException, InterruptedException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = sqlSession.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //发送请求
        JSONArray resultJson = getResult(getUserListCase);
        //等待3秒
        Thread.sleep(3000);
        //查询数据库
        List<User> userList = sqlSession.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u:userList){
            System.out.println("获取的user对象是："+u.toString());
        }
        JSONArray userListJson =new JSONArray(userList);

        //断言
        Assert.assertEquals(userListJson.length(),resultJson.length());//先校验数组长度相同
        for(int i=0;i<resultJson.length();i++){
            Assert.assertEquals(userListJson.get(i).toString(),resultJson.get(i).toString());//再依次校验数组里的每一项
        }
    }

    private JSONArray getResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("age",getUserListCase.getAge());
        param.put("sex",getUserListCase.getSex());

        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"UTF-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }

}
