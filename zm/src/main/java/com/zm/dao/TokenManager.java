package com.zm.dao;

import com.zm.entity.UserInfo;

/**
 * @author xx
 * @date 2019/4/17 17:03
 */
public interface TokenManager {

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    String getToken(UserInfo userInfo);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    UserInfo getUserInfoByToken(String token);

}
