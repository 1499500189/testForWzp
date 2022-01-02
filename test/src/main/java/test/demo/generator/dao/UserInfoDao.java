package test.demo.generator.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import test.demo.generator.entity.UserInfoEntity;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {

}
