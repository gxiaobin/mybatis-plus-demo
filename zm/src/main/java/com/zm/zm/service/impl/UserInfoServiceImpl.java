package com.zm.zm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.zm.entity.UserInfo;
import com.zm.zm.mapper.UserInfoMapper;
import com.zm.zm.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-04-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
