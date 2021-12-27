package com.wzp.demo.controller;

/**
 * @author
 * @date 2021 年 11 月 08 日
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
public class AdminDictController {

   // @Resource
  //  private DictService dictService;

/*
    @PostMapping("/import")
    public Integer batchImport(
            @RequestParam("file") MultipartFile file) {


        ArrayList<Object> objects = new ArrayList<>();
        Map<String, List<Object>> menuGroupMap = objects.stream().collect(Collectors.groupingBy(goods -> goods.getInteger("menu_id") + "_" + goods.getString("menu_name")));


        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        try {
            byte[] bytes = file.getBytes();
            System.out.println("bytes:" + bytes);
            InputStream inputStream = file.getInputStream();
            System.out.println("inputStream  :" + inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // throw  new 创建一个异常
        }
        long size = file.getSize();
        Resource resource = file.getResource();

        System.out.println("name:" + name);
        System.out.println("originalFilename:" + originalFilename);
        System.out.println("contentType" + contentType);
        return 1;


        Object o = new Object();
        if ("是总经理的情况".equals(o)) {
            ArrayList<Object> objects1 = new ArrayList<>();
            objects1.stream().collect()      //根据属性部门进行流分组   得到每个部门的情况


            //遍历这些部门  ， 之后对这些部门继续进行小组分组  ，根据他们的小组进行分组
            //最后展示的为这些组遍历出来之后继续按照小组分组的值，进行拼接


        }
    }*/
}
