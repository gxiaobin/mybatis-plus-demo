//package com.zm.feign;
//
///**
// * @author xiaobin
// * @date 2019/5/8 18:21
// */
//
//import com.zm.base.BaseRes;
//import com.zm.config.FeignConfig;
//import com.zm.entity.UserInfo;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * @author 一个忙来无聊的人
// * @desc ：调用scb-member feign 请求信息
// * @date 2019/5/6 10:23
// */
//@Component
//@FeignClient(name = "scb-member", configuration = FeignConfig.class)
//public interface MemberClientFeign {
//    // TODO 如果不是此处调用  请更改项目名称
//    /**
//     * 新增课程信息请求
//     *
//     * @param req
//     * @return
//     */
//    @PostMapping(value = "/course/createCourse")
//    BaseRes createCourse(@RequestBody UserInfo req);
//
//
//
//}
//
