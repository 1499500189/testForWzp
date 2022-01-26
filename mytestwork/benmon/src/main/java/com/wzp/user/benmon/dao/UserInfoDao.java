package com.wzp.user.benmon.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.wzp.user.benmon.entity.FileTempCusEntity;
import com.wzp.user.benmon.entity.StationEntity;
import com.wzp.user.benmon.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


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

    List<UserInfoEntity> selectByMy(Object o);
 //   Long  selectByMy_COUNT(Object o);

     void   sss(@Param("list")List<FileTempCusEntity> list);
}
