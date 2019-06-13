package com.zm.exception;

import com.zm.util.ResultUtil;
import com.zm.base.BaseRes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 一个忙来无聊的人
 * @date 2019/4/17 17:26
 */

// 拦截器

@ControllerAdvice
public class GlobalDefultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseRes defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return ResultUtil.error(businessException.getRetCode(), businessException.getMessage());
        }
        //未知错误
        return ResultUtil.error("99999", "系统异常：\\n" + e.toString());
    }
}
