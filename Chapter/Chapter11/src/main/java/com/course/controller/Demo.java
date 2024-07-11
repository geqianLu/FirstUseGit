package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping("v1")
public class Demo {

    //首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value ="/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value ="可以获取到用户总数",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value ="新增用户",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        return template.insert("addUser",user);
    }

    @RequestMapping(value ="/updateUserById",method = RequestMethod.POST)
    @ApiOperation(value ="更新用户",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return template.update("updateUserById",user);
    }

    @RequestMapping(value = "/deleteUserById",method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除用户",httpMethod = "DELETE")
    public int deleteUser(@RequestParam Integer id){
        return template.delete("deleteUserById",id);
    }
}
