package com.zm.constant;

/**
 * @author xx
 * @date 2019/4/17 17:07
 */
public class Constants {

    public static final String TOKEN_FORMAT = "token_format";
    // 用户登录cookie
    public final static String USER_TOKEN = "userToken";

    public final static String USER_INFO = "USER_INFO";

    // session中存放登录用户
    public final static String SESSION_USER = "session_user";

    // 指定用户登录过期时间  半个小时
    public final static long REDIS_USER_TIME = 30 * 60L;

}
