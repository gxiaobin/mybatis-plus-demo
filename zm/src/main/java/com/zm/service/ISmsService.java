package com.zm.service;

/**
 * @author xiaobin
 * @date 2019/5/8 17:36
 */
public interface ISmsService {
    boolean sendMsg(String phone, String templateCode, String templateParam);
}
