package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i = template.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("login查询的的结果是"+i);
        if(i==1){
            log.info("登录的用户是"+user.getUserName());
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    public Boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        int result = 0;
        if(x != false){
            result = template.insert("addUser",user);
        }
        if(result > 0){
            log.info("添加的用户数量是"+ result);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户（列表）信息接口",httpMethod = "POST")
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        if(x == true){
           List<User> users =  template.selectList("getUserInfo",user);
           log.info("查询到的用户总数是"+ users.size());
           return users;
        }
        return null;
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "根据id更新/删除用户接口",httpMethod = "POST")
    public int updateUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        int i = 0;
        if(x == true){
            i = template.update("updateUserInfo",user);
            log.info("更新的用户数量是"+ i);
        }
        return i;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookies为空");
            return false;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }
        log.info("cookies验证不通过");
        return false;
    }
}
