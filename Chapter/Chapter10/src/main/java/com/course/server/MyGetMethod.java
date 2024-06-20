package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    /**
     * 返回cookies的get请求
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value ="通过这个方法可以获取cookies", httpMethod = "GET")
    public String getCookies (HttpServletResponse response){

        //HttpServerRequest  装请求信息的类
        //HttpServerResponse 装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功！！！";
    }

    /**
     * 需要携带cookies才能访问的get请求
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value="要求客户端携带cookies才能访问的get请求",httpMethod = "GET")
    public String getWithCookies (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "请携带cookies来访问";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "携带了正确的cookies，成功访问了该请求";
            }
        }
        return "请携带正确的cookies来访问";
    }

    /**
     * 需要携带参数才能访问的get请求
     * 方式一：url?key1=value1&key2=value2
     * 模拟返回商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求的方法一",httpMethod = "GET")
    public Map<String,Integer> getList (@RequestParam Integer start,
                                        @RequestParam Integer end){
        Map<String,Integer> mylList = new HashMap<>();
        mylList.put("运动鞋",400);
        mylList.put("干脆面",1);
        mylList.put("衬衫",200);

        return mylList;
    }

    /**
     * 需要携带参数才能访问的get请求
     * 方式二：url/{value1}/{value2}
     * 模拟返回商品列表
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求的方法二",httpMethod = "GET")
    public Map<String,Integer> getList2 (@PathVariable Integer start,
                                        @PathVariable Integer end){
        Map<String,Integer> mylList = new HashMap<>();
        mylList.put("运动鞋",401);
        mylList.put("干脆面",2);
        mylList.put("衬衫",201);

        return mylList;
    }

}
