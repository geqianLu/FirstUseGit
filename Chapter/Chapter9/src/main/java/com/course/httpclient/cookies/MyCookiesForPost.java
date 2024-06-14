package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String host;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){

        //从配置文件读取host
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        host = bundle.getString("test.host");
    }

    @Test
    public void testGetCookies() throws IOException {

        String uri = this.bundle.getString("test.getCookies.uri");
        String testUrl = this.host + uri;

        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = this.store.getCookies();

        for(Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.print("cookie name: "+name+"; cookie value: "+value);
        }
    }

    @Test(dependsOnMethods = "testGetCookies")
    public void testPostWithCookies() throws IOException {

        String uri = this.bundle.getString("test.post.with.cookies.uri");
        String testUrl = this.host + uri;

        HttpPost post = new HttpPost(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置请求headers
        post.setHeader("content-type","application/json");

        //设置cookies
        client.setCookieStore(this.store);

        //设置json格式请求参数
        JSONObject param = new JSONObject();
        param.put("name","zhangsan");
        param.put("age","18");

        //将参数添加到post方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //执行post请求
        HttpResponse response = client.execute(post);

        String result =EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("该post请求的返回是："+result);

        //取出返回值
        JSONObject resultJson = new JSONObject(result);
        String success = (String) resultJson.get("zhangsan");
        String code = (String) resultJson.get("code");

        //断言
        Assert.assertEquals("success",success);
        Assert.assertEquals("12138",code);
    }

}
