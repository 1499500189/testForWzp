package com.wzp.demo.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wzp.demo.generator.dao.UserInfoDao;
import com.wzp.demo.generator.entity.UserInfoEntity;
import com.wzp.demo.generator.service.UserInfoService;
import org.springframework.stereotype.Service;



@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {


    @Override
    public void save() {

            baseMapper.insert(new UserInfoEntity());
    }
}
