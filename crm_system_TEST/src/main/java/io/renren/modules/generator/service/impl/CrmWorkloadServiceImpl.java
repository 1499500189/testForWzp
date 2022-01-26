package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.employee.constant.ConstantRoles;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CrmWorkloadDao;
import io.renren.modules.generator.entity.CrmWorkloadEntity;
import io.renren.modules.generator.service.CrmWorkloadService;


@Service("crmWorkloadService")
public class CrmWorkloadServiceImpl extends ServiceImpl<CrmWorkloadDao, CrmWorkloadEntity> implements CrmWorkloadService {

     @Autowired
     private SysUserService sysUserService;
     @Autowired
     private EmployeeInfoService employeeInfoService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmWorkloadEntity> page = this.page(
                new Query<CrmWorkloadEntity>().getPage(params),
                new QueryWrapper<CrmWorkloadEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage<CrmWorkloadEntity> selectWorkloadList(Map<String, Object> params, SysUserEntity currentUser) {

        String roleName = sysUserService.getCurrentUserRoleName(currentUser);
        if (!roleName.equals(ConstantRoles.OW)) {
            EmployeeInfoEntity employeeInfoEntity = employeeInfoService.queryUserId(currentUser.getUserId());
            QueryWrapper<EmployeeInfoEntity> emWrapper = new QueryWrapper<>();
            emWrapper.likeRight("codes", employeeInfoEntity.getCodes());
            List<EmployeeInfoEntity> emList = employeeInfoService.list(emWrapper);
            params.put("emList", emList);
        }
        long page = Long.parseLong((String) params.get("page"));
        long limit = Long.parseLong((String) params.get("limit"));
        Page<CrmWorkloadEntity> iPage = new Page<>(page, limit);
        IPage<CrmWorkloadEntity> workbenchList = baseMapper.selectWorkloadList(iPage, params);

        return workbenchList;
    }

    @Override
    public CrmWorkloadEntity getWorkload(SysUserEntity user, Integer id) {

        CrmWorkloadEntity crmWorkloadEntity = baseMapper.selectById(id);
        Long userId = crmWorkloadEntity.getUserId();
        SysUserEntity userEntity = sysUserService.getById(userId);
        if (null!=userEntity){
            crmWorkloadEntity.setUsername(userEntity.getUsername());
        }
        return crmWorkloadEntity;
    }

}
