package com.wzp.user.benmon.controller;

import com.github.pagehelper.PageInfo;
import com.wzp.user.benmon.entity.UserInfoEntity;
import com.wzp.user.benmon.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @date 2022 年 01 月 11 日
 */
@Api(tags = "测试保存数据的")
@RestController
@RequestMapping("/filetest")
public class FileTestController {
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 列表
     */
    @GetMapping("/insertOne")
    @ApiOperation("列表保存")
    public List<UserInfoEntity> InsertOne(/*@RequestParam Map<String, Object> params*/){
        //userInfoService.getsave();

        //我要找一个问题是，更新的时候能不能把某个字段置为空 null  。

          userInfoService.insertFileInfo();
        return   null;
    }
}
