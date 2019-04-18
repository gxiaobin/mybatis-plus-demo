package com.zm.zm.fileter;

import com.zm.zm.condstant.Constants;
import com.zm.zm.exception.BusinessException;
import com.zm.zm.user.AuthIgnore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xx
 * @date 2019/4/17 17:19
 * @desc 拦截接口信息
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }

        //如果有@AuthIgnore注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //获取用户凭证
        String token = request.getHeader(Constants.USER_TOKEN);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(Constants.USER_TOKEN);
        }
        if (StringUtils.isBlank(token)) {
            Object obj = request.getAttribute(Constants.USER_TOKEN);
            if (null != obj) {
                token = obj.toString();
            }
        }

        //token凭证为空
        if (StringUtils.isBlank(token)) {
              throw new BusinessException(HttpStatus.UNAUTHORIZED.getReasonPhrase(), Constants.USER_TOKEN + "不能为空");
        }
        return true;
    }
}