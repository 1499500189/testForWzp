package com.wzp.user.benmon.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;


import com.github.pagehelper.PageInfo;
import com.wzp.user.benmon.entity.UserInfoEntity;
import com.wzp.user.benmon.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
@Api(tags = "测试列表 ，去保存数据的")
@RestController
@RequestMapping("/userinfo1")
public class UserInfoController1 {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表保存")
    public List<UserInfoEntity> list(/*@RequestParam Map<String, Object> params*/){
        //userInfoService.getsave();

        //我要找一个问题是，更新的时候能不能把某个字段置为空 null  。

        PageInfo<UserInfoEntity> userInfoEntityPageInfo = userInfoService.listAllLimit();
        System.out.println(userInfoEntityPageInfo);
        System.out.println(userInfoEntityPageInfo.getTotal());
        System.out.println("___________");
        System.out.println(userInfoEntityPageInfo.getList().size());
        System.out.println(userInfoEntityPageInfo.getList());

       return userInfoEntityPageInfo.getList();
    }
    @GetMapping("/update")
    @ApiOperation("列表保存")
    public void update(){

        //我要找一个问题是，更新的时候能不能把某个字段置为空 null  。
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(1L);
        userInfoEntity.setNamw(null);
        userInfoEntity.setLocation("hhh");

        UpdateWrapper<UserInfoEntity> userInfoEntityUpdateWrapper = new UpdateWrapper<>();
/*        userInfoEntityUpdateWrapper.eq("id",1);*/
  /*      userInfoEntityUpdateWrapper.set("namw",null);*/
        userInfoEntityUpdateWrapper.set("namw",null);
        userInfoService.update(userInfoEntity,userInfoEntityUpdateWrapper);
    }
    @GetMapping("/getOneUser")
    @ApiOperation("列表保存")
    public void getOneUser(){
     userInfoService.getOneUser();
        System.out.println("获取到信息了   ，之后测试pageHelper是否是好使 的");
    }
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public  void  upload(@RequestParam("file") MultipartFile file){
        FileOutputStream fos =null;
        FileInputStream fis=null;
        try {
    /*        文件的拷贝 ，文件的复制原理  ，由D盘的文件拷贝到c盘下 ，  需要先经过内存 ，
            先读入到内存当中
            之后文件输出到本地的磁盘中 ，读和写   ，一边读 一边写
            这个是本地化到磁盘的 ，阿里云的更加简单，主要是文件的目录的名字

            使用FileInputStream 和 FileOutputStream 进行拷贝  ，使用以上的字节流拷贝文件的时候，
            文件的类型是随意的，万能的 。什么样的文件都能拷贝 ，但是有大小限制，需要设置 ，因为流要转换 ，转换成byte数组，
            数组的长度有限制，所以上传过大的文件会出现异常，可以处理这个异常， 返回展示 。

            */
              //创建一个输入流的文件 ，
                   /* InputStream */
            InputStream inputStream = file.getInputStream();
            //之后可以采用边读边写的方式，
            long l = System.currentTimeMillis();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            String name = file.getName();
            System.out.println(name);
            File file1 = new File("D:\\d\\option\\11\\22\\23\\44\\55\\66");
            if(!file1.exists()&&!file1.isDirectory()) {  //如果目录不存在 ，创建目录使用的方式
                file1.mkdirs();
            }
            fos =       new FileOutputStream("D:\\d\\option\\11\\22\\23\\44\\55\\66\\"+originalFilename);
            //创建文件夹

            fos.write(buffer);
            System.out.println("复制完毕");
            long l1 = System.currentTimeMillis();
            System.out.println(l1-l);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.getMessage();
        }
        finally {

                if (fos!=null){
                    try {
                        fos.close();
                    }catch (IOException e){
                        e.getStackTrace();
                    }
                }
                if (fis!=null){
                    try {
                        fis.close();
                    }catch (IOException e){
                        e.getStackTrace();
                    }
                }
        }

    }
    @PostMapping("/deleteFile")
    @ApiOperation("删除文件")
    public  void  deleteFile(@RequestParam("file") MultipartFile file){
        FileOutputStream fos =null;
        FileInputStream fis=null;
        try {
            InputStream inputStream = file.getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            String name = file.getName();
            System.out.println(name);
            File file1 = new File("D:\\d\\option\\11\\22\\23\\44\\55\\66\\"+".eslintrc.js");
            file1.delete();
          /*  fis =       new FileInputStream("D:\\d\\option\\11\\22\\23\\44\\55\\66"+originalFilename);
            //创建文件夹
            int read = fis.read();
            System.out.println("复制完毕");*/
            System.out.println("删除完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.getMessage();
        }
        finally {

            if (fos!=null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.getStackTrace();
                }
            }
            if (fis!=null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.getStackTrace();
                }
            }
        }

    }
    @PostMapping("/upload2")
    @ApiOperation("文件上传2")
    public  void  upload2(@RequestParam("file") MultipartFile file){
        FileOutputStream fos =null;
        FileInputStream fis=null;
        try {
    /*        文件的拷贝 ，文件的复制原理  ，由D盘的文件拷贝到c盘下 ，  需要先经过内存 ，
            先读入到内存当中
            之后文件输出到本地的磁盘中 ，读和写   ，一边读 一边写
            这个是本地化到磁盘的 ，阿里云的更加简单，主要是文件的目录的名字

            使用FileInputStream 和 FileOutputStream 进行拷贝  ，使用以上的字节流拷贝文件的时候，
            文件的类型是随意的，万能的 。什么样的文件都能拷贝 ，但是有大小限制，需要设置 ，因为流要转换 ，转换成byte数组，
            数组的长度有限制，所以上传过大的文件会出现异常，可以处理这个异常， 返回展示 。

            */
            //创建一个输入流的文件 ，
            /* InputStream */
            InputStream inputStream = file.getInputStream();
            //之后可以采用边读边写的方式，
            long l = System.currentTimeMillis();
          //  byte[] buffer = new byte[inputStream.available()];


           // inputStream.read(buffer);
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            String name = file.getName();
            System.out.println(name);
            File file1 = new File("D:\\d\\option\\11\\22\\23\\44\\55\\66");
            if(!file1.exists()&&!file1.isDirectory()) {  //如果目录不存在 ，创建目录使用的方式
                file1.mkdirs();
            }
            file.transferTo(new File("D:\\d\\option\\11\\22\\23\\44\\55\\66\\"+originalFilename));

          /*  fos =       new FileOutputStream("D:\\d\\option\\11\\22\\23\\44\\55\\66\\"+originalFilename);
            //创建文件夹
            byte[] bytes= new byte[1024*1024]; //1MB(一次最多拷贝1MB.);\
            int readCount =0;
            while ((readCount=fis.read(bytes))!=-1){

                fos.write(bytes,0,readCount);
            }*/
            System.out.println("复制完毕");
            long l1 = System.currentTimeMillis();
            System.out.println(l1-l);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.getMessage();
        }
        finally {

            if (fos!=null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.getStackTrace();
                }
            }
            if (fis!=null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.getStackTrace();
                }
            }
        }

    }





}
