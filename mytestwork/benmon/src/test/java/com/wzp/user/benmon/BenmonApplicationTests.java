package com.wzp.user.benmon;

import com.github.pagehelper.PageInfo;
import com.wzp.user.benmon.entity.UserInfoEntity;
import com.wzp.user.benmon.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BenmonApplicationTests {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    void contextLoads() {

        //userInfoService.getsave();

        //我要找一个问题是，更新的时候能不能把某个字段置为空 null  。

        PageInfo<UserInfoEntity> userInfoEntityPageInfo = userInfoService.listAllLimit();
        System.out.println(userInfoEntityPageInfo);
        System.out.println(userInfoEntityPageInfo.getTotal());
        System.out.println("______________");
        System.out.println(userInfoEntityPageInfo.getList());

        List<String> list = new ArrayList<String>();
        list.add("name");
        list.add("臧三");
        list.add("name");
        list.add("李四");
        list.add("name");
        list.add("李四");
        list.add("name");
        list.add("李四");

        try {
//将集合数据写入磁盘
            FileWriter fw = new FileWriter("D:\\d\\name.txt");

            for (String s : list) {
                fw.write(s);
                fw.write("\r\n");
            }
            fw.close();

        } catch (IOException e) {
            e.getStackTrace();
        }


    }


    @Test
    void test2() {

        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
