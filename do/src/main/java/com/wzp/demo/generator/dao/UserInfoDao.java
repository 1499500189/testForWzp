package com.wzp.demo.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wzp.demo.generator.entity.UserInfoEntity;
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

}
