package com.zm.zm.base;



/**
 * @author xx
 * @date 2019/4/16 16:35
 */
public class BaseRes {

    private String retCode = "000000";// retCode 000001 代码成功 000000 失敗

    private String retMsg = "失败"; // 返回消息信息

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

    public BaseRes() {
        this.retCode = "000000";
        this.retMsg = "失败";
    }

    public BaseRes(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
}
