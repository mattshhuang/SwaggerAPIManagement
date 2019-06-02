package com.ustc.apimsservice.controller;

import com.ustc.apimsservice.bean.Service;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service API
 * @Author Matthew Huang
 * @Date 2019-5-23 10:10
 */
@RestController
@RequestMapping(path = "/services",  produces = "application/json; charset=utf-8")
public class ServiceController {
    static Map<Long, Service> map = new HashMap<>();

    /**
     * @name: getList
     * @description: get service list
     * @param: []
     * @return: java.util.List<Service>
     */
    @ApiOperation(value = "获取服务列表", notes = "直接查看当前所有服务列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Service> getList() {
        List<Service> list = new ArrayList<>(map.values());
        return list;
    }


    /**
     * @name: postService
     * @description: create service
     * @param: [service]
     * @return: java.lang.String
     */
    @ApiOperation(value = "创建服务" , notes = "根据Service对象属性创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  , value = "服务id" , required = true, dataType = "Long"   , paramType = "query"),
            @ApiImplicitParam(name = "name", value = "服务名称", required = true, dataType = "String" , paramType = "query"),
            @ApiImplicitParam(name = "type" , value = "服务种类", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "time", value = "服务时间", required = true, dataType = "double", paramType = "query")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postService (@RequestParam Long id, @RequestParam String name, @RequestParam String type, @RequestParam double time) {

        Service service = new Service(id, name, type, time);
        map.put(service.getId(), service);
        return "{\"response\":\"添加成功\"}";
    }


    /**
     * @name: getServiceById
     * @description: get user message by service id
     * @param: [id]
     * @return: Service
     */
    @ApiOperation(value = "获取服务详情",notes = "根据url的id来获取服务基本信息")
    @ApiImplicitParam(name = "id", value = "服务id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Service getServiceById(@PathVariable Long id) {

        return  map.get(id);
    }


    /**
     * @name: putService
     * @description: update service message by service id
     * @param: [id, service]
     * @return: java.lang.String
     */
    @ApiOperation(value = "更新服务信息",notes = "根据id来指定对象，并且根据需求进行服务基本信息更新，无需更新的信息保持默认值即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "服务名称", dataType = "String" , paramType = "query", defaultValue = "null"),
            @ApiImplicitParam(name = "type" , value = "服务种类", dataType = "String", paramType = "query", defaultValue = "null"),
            @ApiImplicitParam(name = "time", value = "服务时间", dataType = "double" , paramType = "query", defaultValue = "-1")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putService(@PathVariable Long id, @RequestParam String name, @RequestParam String type, @RequestParam double time) {
        Service service = map.get(id);
        if (time != -1)
            service.setTime(time);
        if (!name.equals("null")) {
            service.setName(name);
        }
        if (!type.equals("null"))
            service.setType(type);
        map.put(id, service);
        return "{\"response\":\"服务基本信息已经更新成功\"}";
    }


    /**
     * @name: delService
     * @description: delete service by service id
     * @param: [id]
     * @return: java.lang.String
     */
    @ApiOperation(value = "删除服务", notes = "根据url的id来指定对象，进行服务信息删除")
    @ApiImplicitParam(name = "id", value = "服务id", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delService(@PathVariable Long id) {
        map.remove(id);
        return "{\"response\":\"服务ID为:" + id + "的服务已经被移除系统\"}";
    }
}
