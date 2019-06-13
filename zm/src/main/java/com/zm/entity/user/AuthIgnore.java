package com.zm.entity.user;

import java.lang.annotation.*;

/**
 * @author 一个忙来无聊的人
 * @date 2019/4/17 17:16
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
