 package com.zm.condstant;
/**
 *  操作返回信息：提示，异常等
 * @author Administrator
 *
 */
public enum ResposeMsgEnum {
	//操作结果
	OP_FAILURE("000000", "操作失败"),
	OP_SUCCESS("000001", "操作成功"),
	OP_DUPLICATED("000003", "已存在相同记录"), 
	//错误信息
	ERR_LOGIN_ACCOUNT("400000", "用户或密码错误"),
	ERR_SESSION_EXPIRE("400001", "登录信息过期 "),
	ERR_ACCCESS_LIMIT("400002", "您的权限不够"),
	ERR_REQ_PARAM("400003", "请求参数异常"),
	ERR_UPLOAD_FILE("400004", "上传文件错误");
 
	//----------------------------
    private String key;
    private String msg;

    public String getKey() {
        return key;
    } 

    public String getMsg() {
        return msg;
    } 
    private ResposeMsgEnum(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }
   
}
