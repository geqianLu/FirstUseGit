package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

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
    public void testGetWithCookies() throws IOException {

        String uri = this.bundle.getString("test.get.with.cookies.uri");
        String testUrl = this.host + uri;

        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies
        client.setCookieStore(this.store);

        //执行get请求
        HttpResponse response = client.execute(get);

        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+statusCode);

        if(statusCode==200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }

    }


}
