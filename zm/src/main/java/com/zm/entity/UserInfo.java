package com.zm.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 一个忙来无聊的人
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(fluent = true)若为true，则getter和setter方法的方法名都是属性名  如  public Integer id(){}
//@Accessors(chain = true) 若为true，则setter方法返回当前对象  public User setId(Integer id){}
// @Accessors(prefix = "true") 若为true，则getter和setter方法会忽视属性名的指定前缀（遵守驼峰命名）
@Accessors(chain = true)
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serVersionUID = 1L;

    private Integer  id;

    private String loginName;

    private String name;

    private String pwd ;

    private Date createTime;

    private Date modifyTime;

    private String deleteFlag;

    private String statusFlag;

}
