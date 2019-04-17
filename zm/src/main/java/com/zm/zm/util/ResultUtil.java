package com.zm.zm.util;

import com.zm.zm.base.BaseRes;

/**
 * @author xiaobin
 * @date 2019/4/17 17:29
 */
public class ResultUtil {

    public static BaseRes error(String retCode, String retMsg) {
        return new BaseRes(retCode, retMsg);
    }
}
