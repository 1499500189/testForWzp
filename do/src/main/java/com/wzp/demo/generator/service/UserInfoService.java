package com.wzp.demo.generator.service;



import com.baomidou.mybatisplus.service.IService;
import com.wzp.demo.generator.entity.UserInfoEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    void save();
}

