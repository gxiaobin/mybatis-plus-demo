package com.zm.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zm.service.ISmsService;
import com.zm.util.JacksonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Desc 短信发送接口
 * @author xiaobin
 * @date 2019/5/8 17:36
 */
@Service
public class SmsServiceImpl implements ISmsService {
    private static final Logger logger = LogManager.getLogger(SmsServiceImpl.class);

    //替换成你的AccessKey  // TODO 此处需要填写 AccessKey
    final String accessKeyId = "LTAIBN2NaYN6K0o3";//你的accessKeyId,参考本文档步骤2
    final String accessKeySecret = "RR2de6ASdU1dCngH62RoSBPD1jH3DS";//你的accessKeySecret，参考本文档步骤2

    /**
     * 短信发送接口信息  支持批量发送 ps--目前签名信息仅设置一个
     *
     * @param phone         需要发送的电话号码，支持多个电话号码 格式为"13600000000,15000000000"
     * @param templateCode  明确需要使用哪个模板，可以从阿里云控制台查看
     * @param templateParam 模板内需要填充的字段及字段值 格式为("{\"name\":\"Tom\", \"code\":\"123\"}")
     * @Return true 代表发送成功  false 代表发送失败
     */
    @Override
    public boolean sendMsg(String phone, String templateCode, String templateParam) {
        boolean bool = false;
        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25"); // 版本信息  已经固定  不能进行更改
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.putQueryParameter("PhoneNumbers", phone);
        // mz签名 阿里云控制台签名
        String signName = "美中教育";
        request.putQueryParameter("SignName", signName);
        // 阿里云控制台模板编号
        request.putQueryParameter("TemplateCode", templateCode);
        request.setAction("SendSms"); //系统规定参数
        // 模板内需要填充参数信息
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            logger.info("调用阿里云短信服务请求 phone={}，templateCode={},templateParam={}", phone, templateCode, templateParam);
            CommonResponse response = client.getCommonResponse(request);
            logger.info("调用阿里云发送短信响应为：{}", response.getData());
            Map<String, Object> map = JacksonUtil.json2Map(response.getData());
            if ("OK".equals(map.get("Code"))) {
                bool = true;
            }
        } catch (ServerException e) {
            logger.error("阿里云短信服务异常:{}", e);
        } catch (ClientException e) {
            logger.error("连接阿里云短信异常:{}", e);
        } catch (Exception e) {
            logger.error("json转换异常:{}", e);
        }
        return bool;

    }
}
