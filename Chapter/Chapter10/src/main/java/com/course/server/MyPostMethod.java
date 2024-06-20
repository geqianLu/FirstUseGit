package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post方法")
@RequestMapping("/v1")
public class MyPostMethod {

    //这个变量用来装cookies信息
    private static Cookie coookies;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，登录成功后返回cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value ="userName",required = true) String userName,
                        @RequestParam(value ="passWord",required = true) String passWord){
        if(userName.equals("zhangsan") && passWord.equals("123456")){
            coookies = new Cookie("login","true");
            response.addCookie(coookies);
            return "恭喜你登录成功啦！！！";
        }
        return "用户名或密码错误";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        //获取cookies
        Cookie[] cookies = request.getCookies();

        //验证cookies、userName、passWord合法
        User user = new User();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login")
            && cookie.getValue().equals("true")
            && u.getUserName().equals("zhangsan")
            && u.getPassWord().equals("123456")){
                user.setUserName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }



}
