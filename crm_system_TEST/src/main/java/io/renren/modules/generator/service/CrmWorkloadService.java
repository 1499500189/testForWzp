package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.entity.CrmWorkloadEntity;
import io.renren.modules.sys.entity.SysUserEntity;

import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-01-24 14:20:25
 */
public interface CrmWorkloadService extends IService<CrmWorkloadEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage<CrmWorkloadEntity> selectWorkloadList(Map<String, Object> params, SysUserEntity user);

    CrmWorkloadEntity getWorkload(SysUserEntity user, Integer id);
}

