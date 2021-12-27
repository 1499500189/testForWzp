package com.wzp.demo.generator.controller;

import java.util.Arrays;
import java.util.Map;

import com.wzp.demo.generator.entity.UserInfoEntity;
import com.wzp.demo.generator.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
@RestController
@RequestMapping("generator/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public void list(@RequestParam Map<String, Object> params){

    }




}
