package com.zm.zm.controller;


import com.zm.zm.entity.UserInfo;
import com.zm.zm.mapper.UserInfoMapper;
import com.zm.zm.service.impl.UserInfoServiceImpl;
import com.zm.zm.user.AuthIgnore;
import com.zm.zm.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xx
 * @since 2019-04-15
 */
@RestController
@RequestMapping("/user")
@Api("用户信息相关API")
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisUtil redis;

    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询用户")
    public UserInfo getUserInfo() {
        UserInfo byId = userInfoServiceImpl.getById(1);
        userInfoServiceImpl.query();
        redis.set("myname", "my name is job !");
        return byId;
    }

    @RequestMapping(value = "/getuserInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询列表")
    public List<UserInfo> getUser() {
        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        return userInfos;
    }

    @RequestMapping(value = "/setRedis", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "设置redis")
    @AuthIgnore
    public String setRedis() {
        redis.set("myname", "my name is job !");
        return "0";
    }

    @RequestMapping(value = "/getRedisValue", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取redis")
    @AuthIgnore
    public String getRedisValue() {
        String myname = redis.get("myname").toString();
        return myname;
    }

}
