package com.zm.zm.user;

import com.zm.zm.condstant.Constants;
import com.zm.zm.dao.TokenManager;
import com.zm.zm.entity.UserInfo;
import com.zm.zm.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author xx
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
        String token_format=String.format(Constants.TOKEN_FORMAT,token);
//        redisUtils.set(token_format,userInfo,globalConfig.getTokenExpires());
        // TODO 未设置
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
            //TODO 未做redis 刷新功能
//            redisUtils.setExpireTime(token, globalConfig.getTokenExpires());
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
