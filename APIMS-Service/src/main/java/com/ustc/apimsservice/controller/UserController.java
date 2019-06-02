package com.ustc.apimsservice.controller;

import com.ustc.apimsservice.bean.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User API
 * @author matthew huang
 * @description
 * @date 2019/5/22 3:50 PM
 */
@RestController
@RequestMapping(path = "/users",  produces = "application/json; charset=utf-8")
public class UserController {

    static Map<Long, User> map = new HashMap<>();

    /**
     * @name: getList
     * @description: get user list
     * @param: []
     * @return: java.util.List<User>
     */
    @ApiOperation(value = "获取用户列表", notes = "直接查看当前所有用户列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getList() {
        List<User> list = new ArrayList<>(map.values());
        return  list;
    }


    /**
     * @name: postUser
     * @description: create user
     * @param: [user]
     * @return: java.lang.String
     */
    @ApiOperation(value = "创建用户" , notes = "根据user对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  , value = "用户id" , required = true, dataType = "Long"   , paramType = "query"),
            @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String" , paramType = "query"),
            @ApiImplicitParam(name = "age" , value = "用户年龄", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "accessToken", value = "用户访问令牌", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser (@RequestParam Long id, @RequestParam String name, @RequestParam Integer age, @RequestParam String accessToken) {

        User user = new User(id, name, age, accessToken);
        map.put(user.getId(),user);
        return "{\"response\":\"添加成功\"}";
    }


    /**
     * @name: getUserById
     * @description: get user message by user id
     * @param: [id]
     * @return: User
     */
    @ApiOperation(value = "获取用户详情",notes = "根据url的id来获取用户基本信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {

        return  map.get(id);
    }

    /**
     * @name: putUser
     * @description: update user message by user id
     * @param: [id, user]
     * @return: java.lang.String
     */
    @ApiOperation(value = "更新用户信息",notes = "根据id来指定对象，并且根据需求进行用户基本信息更新，无需更新的信息保持默认值即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "用户姓名", dataType = "String" , paramType = "query", defaultValue = "null"),
            @ApiImplicitParam(name = "age" , value = "用户年龄", dataType = "Integer", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "accessToken", value = "用户访问令牌", dataType = "String" , paramType = "query", defaultValue = "null")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestParam String name, @RequestParam Integer age, @RequestParam String accessToken) {
        User u = map.get(id);
        if (age != 0)
            u.setAge(age);
        if (!name.equals("null")) {
            u.setName(name);
        }
        if (!accessToken.equals("null"))
            u.setAccessToken(accessToken);
        map.put(id, u);
        return "{\"response\":\"用户基本信息已经更新成功\"}";
    }


    /**
     * @name: delUser
     * @description: delete user by user id
     * @param: [id]
     * @return: java.lang.String
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定对象，进行用户信息删除")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delUser(@PathVariable Long id) {
        map.remove(id);
        return "{\"response\":\"用户ID为:" + id + "的用户已经被移除系统\"}";
    }

}
