package com.zm.entity.user;

import com.zm.entity.UserInfo;
import com.zm.util.RedisUtil;
import com.zm.constant.Constants;
import com.zm.service.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 一个忙来无聊的人
 * @date 2019/4/17 17:06
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisUtil redisUtils;

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    @Override
    public String getToken(UserInfo userInfo){
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        String tokenFormat=String.format(Constants.TOKEN_FORMAT, token);
        redisUtils.setKeyTime(tokenFormat, userInfo, 3000L);
        return token;
    }

    /**
     * 刷新用户
     * @param token
     */
    @Override
    public void refreshUserToken(String token){
        token=String.format(Constants.TOKEN_FORMAT,token);
        if(redisUtils.exists(token)){
            redisUtils.expire(token, 3000);
        }
    }

    /**
     * 用户退出登陆
     * @param token
     */
    @Override
    public void loginOff(String token){
        token=String.format(Constants.TOKEN_FORMAT,token);
        redisUtils.remove(token);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public UserInfo getUserInfoByToken(String token){
        token=String.format(Constants.TOKEN_FORMAT,token);
        if(redisUtils.exists(token)){
            return (UserInfo)redisUtils.get(token);
        }
        return null;
    }
}
