package com.zm.entity.user;

import com.zm.base.BaseRes;

/**
 * @author xiaobin
 * @date 2019/4/18 15:29
 */
public class LoginRes extends BaseRes {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginRes() {
        super();
    }

    public LoginRes(String retCode, String retMsg) {
        super();
        this.setRetCode(retCode);
        this.setRetMsg(retMsg);
    }
}
