package test.demo.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import test.demo.generator.dao.UserInfoDao;
import test.demo.generator.entity.UserInfoEntity;
import test.demo.generator.service.UserInfoService;


@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {


    @Override
    public void save() {

           /* baseMapper.insert(new UserInfoEntity());*/
    }

    @Override
    public void getsave() {
        baseMapper.insert(new UserInfoEntity());
    }
}
