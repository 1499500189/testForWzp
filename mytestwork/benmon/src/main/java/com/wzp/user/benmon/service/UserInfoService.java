package com.wzp.user.benmon.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wzp.user.benmon.entity.UserInfoEntity;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    void save();

    void getsave();

    void getOneUser();

    PageInfo<UserInfoEntity> listAllLimit();

}

