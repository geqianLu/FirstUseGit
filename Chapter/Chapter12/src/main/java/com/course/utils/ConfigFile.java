package com.course.utils;

import com.course.model.InterfaceName;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    /**
     * 从配置文件获取待测试接口的url
     * @param interfaceName
     * @return
     */
    public static String getUrl(InterfaceName interfaceName){
        String host = bundle.getString("test.url");
        String uri = "";
        //完整的测试url
        String testUrl;

        if(interfaceName == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }

        if(interfaceName == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }

        if(interfaceName == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        if(interfaceName == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }

        if(interfaceName == InterfaceName.ADDUSER){
            uri = bundle.getString("addUser.uri");
        }

        testUrl = host + uri;
        return testUrl;
    }

}
