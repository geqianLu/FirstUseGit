package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestConfig {

    //申明几个静态变量，用于存储请求url、httpclient、cookies信息
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserListUrl;
    public static String getUserInfoUrl;
    public static String addUserUrl;

    public static DefaultHttpClient defaultHttpClient;
    public static CookieStore store;
}
