package com.zm.util;

import com.zm.base.BaseRes;

/**
 * @author xx
 * @date 2019/4/17 17:29
 */
public class ResultUtil {

    public static BaseRes error(String retCode, String retMsg) {
        return new BaseRes(retCode, retMsg);
    }
}
