package test.demo.mon.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzp.test.mon.dao.UserInfoDao;
import com.wzp.test.mon.entity.UserInfoEntity;
import com.wzp.test.mon.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        UserInfoEntity userInfoEntity = userInfoDao.selectByMapId(1);
        System.out.println(userInfoEntity);
        System.out.println(userInfoEntity.getStationEntity());
    }


}
