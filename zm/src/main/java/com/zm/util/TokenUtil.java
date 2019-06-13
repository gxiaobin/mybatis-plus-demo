/**
 * 
 */
package com.zm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zm.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <token：用于web请求访问识别 >
 * @author Administrator
 *
 */
@Component
public class TokenUtil {
    @Autowired
    RedisUtil redisUtil;
    //token秘钥  24 位
    private static final String TOKEN_SECRET = "EJHWQK78SI61UB3FJBF26BQE";
    //前端上传Token字段
    private static final String USER_TOKEN = "Token";
    /**
     * 生成一个token
     */

    public static String getToken(){
        String uuid = PrimaryKeyUtil.getUUID();
        return uuid;
    }
    /***
     * 生成一个会员token
     * */
    public static String getMemberToken(String memberId) {
        String token = "";
        try {
            //过期时间 一个月
           // Date date = JodaTimeUtils.addMonths(1);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带memberId信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("memberId", memberId)
                    //.withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 获取当前操作的后台用户信息
     * */
    public  Map<String,Object> getUserByToken(String token){
        Map<String,Object> userMap = new HashMap<>();
        try {
            if(StringUtils.isNotEmpty(token)) {
                Map<String, Object> map = (Map) redisUtil.get(token);
                String userStr = (String) map.get(Constants.SESSION_USER);
                userMap = JacksonUtil.json2Map(userStr);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userMap;
    }
    /**
     * 获取前台header传入的token字段
     * */
    public static String getUserToken(HttpServletRequest request) {
        String userToken = request.getHeader(USER_TOKEN);
        return userToken;
    }
    public static void main(String[] args) {
        System.out.println(getMemberToken("M100951525726510g2op"));
    }
}
