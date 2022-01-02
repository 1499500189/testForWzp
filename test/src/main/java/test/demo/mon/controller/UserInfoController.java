package test.demo.mon.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wzp.test.mon.entity.UserInfoEntity;
import com.wzp.test.mon.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
@Api(tags = "测试列表 ，去保存数据的")
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表保存")
    public void list(){
        //userInfoService.getsave();

        //我要找一个问题是，更新的时候能不能把某个字段置为空 null  。

      //  return userInfoEntityPageInfo;
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






}
