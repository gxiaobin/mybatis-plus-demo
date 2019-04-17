package com.zm.zm.user;

import java.lang.annotation.*;

/**
 * @author xx
 * @date 2019/4/17 17:16
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
