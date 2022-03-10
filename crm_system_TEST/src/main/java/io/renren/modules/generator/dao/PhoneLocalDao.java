package io.renren.modules.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.generator.entity.CrmWorkloadEntity;
import io.renren.modules.generator.entity.TbTelnumInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-24 14:20:25
 */
@Mapper
public interface PhoneLocalDao extends BaseMapper<TbTelnumInfoEntity> {
}
