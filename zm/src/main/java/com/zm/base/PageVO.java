package com.zm.base;

import java.io.Serializable;

/**
 * <pageVO>
 * desc:
 * Created by  
 */
public class PageVO implements Serializable { 
	private static final long serialVersionUID = 1L;

    public Integer pageNum = 1;  //当前页

    public Integer pageSize = 10; //每页条数

    private Integer start;

    private Integer end;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
