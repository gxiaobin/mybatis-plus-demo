package com.zm.base;

import java.util.Date;

/**
 * @author xx
 * @date 2019/4/17 10:07
 * @desc 后期用于请求头封装
 */
public class BaseReq {

    private Date startTime;

    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
