package com.zm.config;

/**
 * @author xiaobin
 * @date 2019/4/22 15:50
 */

import com.alibaba.fastjson.JSONObject;
import com.zm.condstant.ResposeMsgEnum;
import com.zm.entity.UserInfo;
import com.zm.entity.user.LoginRes;
import com.zm.util.UserInfoUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * token拦截器
 *
 * @author sqc
 * @date 2018/8/2
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LogManager.getLogger(LoginInterceptor.class);

    @Autowired
    private UserInfoUtil userInfoUtil;

    // 步骤1
    public  static LoginInterceptor loginInterceptor ;

    //步骤2 通过@PostConstruct实现初始化bean之前进行的操作
    @PostConstruct
    public void init() {
        loginInterceptor = this;
        loginInterceptor.userInfoUtil = this.userInfoUtil;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String userToken = "";
        try {
            userToken = (String) request.getAttribute("userToken");
            logger.info("usertoken1:" + userToken);
            if (null == userToken){
                userToken = (String) request.getSession().getAttribute("userToken");
            }
            logger.info("usertoken2:" + userToken);
        } catch (Exception e) {
            logger.info("用户未登陆");
            printJson(response, "");
            return false;
        }
        if (null == userToken || "".equals(userToken)) {
            printJson(response, "");
            return false;
        }
        // 步骤三
        UserInfo user = loginInterceptor.userInfoUtil.getUerInfo(userToken);
        if (user == null || StringUtils.isBlank(user.getUserId())) {
            printJson(response, "用户未登录");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 指定拦截指定域名外所有地址
//        response.setHeader("Access-Control-Allow-Origin", "http://my.domain.cn:8080");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    private static void printJson(HttpServletResponse response, String code) {
        LoginRes responseResult = new LoginRes(ResposeMsgEnum.ERR_SESSION_EXPIRE.getKey(), ResposeMsgEnum.ERR_SESSION_EXPIRE.getMsg());
        String content = JSONObject.toJSONString(responseResult);
        printContent(response, content);
    }


    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
