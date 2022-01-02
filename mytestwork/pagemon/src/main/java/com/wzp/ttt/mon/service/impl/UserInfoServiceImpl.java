package com.wzp.ttt.mon.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzp.ttt.mon.dao.UserInfoDao;
import com.wzp.ttt.mon.entity.UserInfoEntity;
import com.wzp.ttt.mon.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service/*("userInfoService")*/
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {
    @Autowired
    private  UserInfoDao userInfoDao;

    @Override
    public void save() {


        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setAge(11);
        userInfoEntity.setLocation("1er");
        try {
            userInfoDao.insert(userInfoEntity);
        }catch (Exception e){
            System.out.println("捕获了异常");
       /*     e.printStackTrace();*/
        }


    }

    @Override
    public void getsave() {
        //baseMapper.insert(new UserInfoEntity());
        userInfoDao.insert(new UserInfoEntity("ww",11,"c"));

    }

    @Override
    public void getOneUser() {
        Page<Object> objects = PageHelper.startPage(1, 1);
        UserInfoEntity userInfoEntity = userInfoDao.selectByMapId(1);
        System.out.println(userInfoEntity);
        System.out.println(userInfoEntity.getStationEntity());
    }

    @Override
    public PageInfo<UserInfoEntity> listAllLimit() {
        int page =1;
        int limit  =10;
          PageHelper.startPage(1,10);
        List<UserInfoEntity> userInfoEntities = baseMapper.selectList(null);
        PageInfo<UserInfoEntity> userInfoEntityPageInfo = new PageInfo<>(userInfoEntities);



      return userInfoEntityPageInfo;

    }
}
