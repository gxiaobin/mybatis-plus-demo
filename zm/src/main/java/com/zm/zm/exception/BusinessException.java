package com.zm.zm.exception;

/**
 * @author xiaobin
 * @date 2019/4/17 17:22
 */
public class BusinessException extends RuntimeException{

    //自定义错误码
    private String retCode  = "999999";;
    private String retMsg = "操作失败，请稍后再试";;

    //自定义构造器，只保留一个，让其必须输入错误码及内容
    public BusinessException(String code,String msg) {
        super(msg);
        this.retCode = code;
        this.retMsg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
