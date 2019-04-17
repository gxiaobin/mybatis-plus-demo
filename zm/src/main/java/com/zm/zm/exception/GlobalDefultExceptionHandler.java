package com.zm.zm.exception;

import com.zm.zm.base.BaseRes;
import com.zm.zm.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xx
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
