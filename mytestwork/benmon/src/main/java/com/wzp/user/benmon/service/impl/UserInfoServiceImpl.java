package com.wzp.user.benmon.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzp.user.benmon.dao.UserInfoDao;
import com.wzp.user.benmon.entity.FileTempCusEntity;
import com.wzp.user.benmon.entity.UserInfoEntity;
import com.wzp.user.benmon.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
         PageHelper.startPage(1, 10);
        UserInfoEntity userInfoEntity = userInfoDao.selectByMapId(1);
        System.out.println(userInfoEntity);
        System.out.println(userInfoEntity.getStationEntity());
    }

    @Override
    public PageInfo<UserInfoEntity> listAllLimit() {

          PageHelper.startPage(1,5);
         // List<UserInfoEntity> userInfoEntities = baseMapper.selectList(null);
       List<UserInfoEntity> userInfoEntities =   baseMapper.selectByMy(null);
        PageInfo<UserInfoEntity> userInfoEntityPageInfo = new PageInfo<>(userInfoEntities);

      return userInfoEntityPageInfo;

    }

    @Override
    public void insertFileInfo() {
        ArrayList<FileTempCusEntity> fs = new ArrayList<>();
        FileTempCusEntity f = new FileTempCusEntity();
        f.setTempId(1);


        fs.add();


    }
}
