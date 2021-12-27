package test.demo.mon.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzp.test.mon.entity.StationEntity;
import com.wzp.test.mon.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-19 15:42:15
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {



    UserInfoEntity  selectByMapId(Integer id) ;
     StationEntity selByPid(Integer id);

}
