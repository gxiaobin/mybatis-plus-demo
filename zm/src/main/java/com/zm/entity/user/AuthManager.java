package com.zm.entity.user;

import com.zm.entity.UserInfo;
import com.zm.exception.BusinessException;
import com.zm.condstant.Constants;
import com.zm.dao.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xx
 * @date 2019/4/17 17:13
 */
@Component
public class AuthManager {

    @Autowired
    private TokenManager tokenManager;

    /**
     * 获取请求体
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 登录
     *
     * @param userInfo
     * @return
     */
    public String signIn(UserInfo userInfo) {
        return tokenManager.getToken(userInfo);
    }

    /**
     * 获取该访问用户信息
     *
     * @return
     */
    public UserInfo getUserInfo() {
        HttpServletRequest request = getRequest();
        String token = request.getAttribute(Constants.USER_TOKEN).toString();
        UserInfo userInfo = tokenManager.getUserInfoByToken(token);
        if (userInfo == null) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "该用户已过期");
        }
        return userInfo;
    }

    /**
     * 刷新该登录用户，延时
     */
    public void refreshUserInfo() {
        HttpServletRequest request = getRequest();
        String token = request.getAttribute(Constants.USER_TOKEN).toString();
        tokenManager.refreshUserToken(token);
    }

    /**
     * 注销该访问用户
     */
    public void loginOff() {
        HttpServletRequest request = getRequest();
        String token = request.getAttribute(Constants.USER_TOKEN).toString();
        tokenManager.loginOff(token);
    }
}
