package test.demo.generator.service;


import com.baomidou.mybatisplus.service.IService;
import test.demo.generator.entity.UserInfoEntity;

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
}

