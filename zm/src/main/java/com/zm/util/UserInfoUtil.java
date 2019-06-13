package com.zm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zm.constant.Constants;
import com.zm.entity.UserInfo;
import com.zm.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置获取用户登录相关信息
 *
 * @author xiaobin
 * @date 2019/4/22 11:19
 */
@Component
public class UserInfoUtil {
    private static Logger logger = LogManager.getLogger(UserInfoUtil.class);
    @Autowired
    private RedisUtil redisUtil;
    // 设置30分钟过期时间
    private static final long EXPIRE_DATE = 30 * 60 * 1000;
    private static final int LOGIN_TIME = 30 * 60;

    //token秘钥  24 位
    private static final String TOKEN_SECRET = "EJHWQK78SI61UB3FJBF26BQE";

    @Autowired
    private UserInfoService userInfoService;

    public UserInfo getUerInfo(HttpSession session) {
        if (!isLogin(session)) {
            return new UserInfo();
        }
        try {
            UserInfo userInfo = (UserInfo) session.getAttribute(Constants.USER_INFO);
            return userInfo;
        } catch (Exception e) {
            logger.error("session未获取到用户信息");
            String token = getToken(session);
            UserInfo user = getUerInfo(token);
            setSession(session, token, user);
            return user;
        }
    }

    public UserInfo getUerInfo(String token) {
        UserInfo user = new UserInfo();
        if (StringUtils.isBlank(token)) {
            return user;
        }
        if (redisUtil.exists(token)) {
            UserInfo userEntity = new UserInfo();
            userEntity.setUserId(String.valueOf(redisUtil.get(token)));
            user = userInfoService.getById(userEntity.getUserId());
            if (user != null) {
                redisUtil.expire(token, Constants.REDIS_USER_TIME);
            }
        }
        return user;
    }

    public boolean isLogin(HttpSession session) {
        String token = getToken(session);
        if (StringUtils.isBlank(token)) {
            return false;
        }
        String id = redisUtil.get(token).toString();
        if (StringUtils.isNotBlank(id)) {
            redisUtil.expire(token, Constants.REDIS_USER_TIME);
            return true;
        }
        return false;
    }

    public static String getToken(HttpSession session) {
        String userToken = String.valueOf(session.getAttribute(Constants.USER_TOKEN));
        if (verify(userToken)) {
            return userToken;
        } else {
            return "";
        }
    }

    public static String getUserToken(String username, String password) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            logger.error("加密失败" + e.toString());
//            throw new CustomizeException();
        }
        return token;
    }

    /**
     * @desc 验证token，通过返回true
     * @create 2019/1/18/018 9:39
     * @params [token]需要校验的串
     **/
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            logger.info(e.toString());
            return false;
        }
    }

    public static void setSession(HttpSession session, String token, UserInfo user) {
        session.setAttribute(Constants.USER_INFO, user);
        //用户无操作30分分钟后重新登录
        session.setAttribute(Constants.USER_TOKEN, token);
        session.setMaxInactiveInterval(LOGIN_TIME);
    }

}
