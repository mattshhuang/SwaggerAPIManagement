package com.ustc.swagger2apimanagementsystemhuawei.web;

import com.ustc.swagger2apimanagementsystemhuawei.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/users")
/**
 * @Description: step4. user control layer
 * @Author: matthew huang
 * @Date: 2019/1/26 3:13 PM
 */
public class UserController {

    static Map<Long, User> map = new ConcurrentHashMap<>();

    @ApiOperation(value = "获取用户列表" , notes = "直接查看当前所有用户列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    /**
     * @name: getList
     * @description: get user list
     * @param: []
     * @return: java.util.List<com.ustc.swagger2apimanagementsystemhuawei.domain.User>
     */
    public List<User> getList() {
        List<User> list = new ArrayList<>(map.values());
        return  list;
    }


    @ApiOperation(value = "创建用户" , notes = "根据user对象创建用户")
    @ApiImplicitParam(name = "user",value = "用户详情实体类(Json格式)",required = true,dataType = "User")
    @RequestMapping(value = "",method = RequestMethod.POST)
    /**
     * @name: postUser
     * @description: create user
     * @param: [user]
     * @return: java.lang.String
     */
    public String postUser(@RequestBody User user) {
        map.put(user.getId(),user);
        return "添加成功!";
    }


    @ApiOperation(value = "获取用户详情",notes = "根据url的id来获取用户基本信息")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long",paramType = "path")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    /**
     * @name: getUserById
     * @description: get user message by user id
     * @param: [id]
     * @return: com.ustc.swagger2apimanagementsystemhuawei.domain.User
     */
    public User getUserById(@PathVariable Long id) {
        return  map.get(id);
    }


    @ApiOperation(value = "更新用户信息",notes = "根据url的id来指定对象，并且根据传过来的user进行用户基本信息更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详情实体类(Json格式)", required = true, dataType = "User")

    })
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    /**
     * @name: putUser
     * @description: update user message by user id
     * @param: [id, user]
     * @return: java.lang.String
     */
    public String putUser(@PathVariable Long id,@RequestBody User user) {
        User u = map.get(id);
        u.setAge(user.getAge());
        u.setName(user.getName());
        map.put(id,u);

        return "用户基本信息已经更新成功!";

    }


    @ApiOperation(value = "删除用户",notes = "根据url的id来指定对象，进行用户信息删除")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long",paramType = "path")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    /**
     * @name: delUser
     * @description: delete user by user id
     * @param: [id]
     * @return: java.lang.String
     */
    public String delUser(@PathVariable Long id) {
        map.remove(id);
        return "用户ID为："+ id + " 的用户已经被移除系统!";

    }
}
