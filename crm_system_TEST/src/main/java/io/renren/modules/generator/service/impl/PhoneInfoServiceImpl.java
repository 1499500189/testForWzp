package io.renren.modules.generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.generator.dao.PhoneLocalDao;
import io.renren.modules.generator.entity.TbTelnumInfoEntity;
import io.renren.modules.generator.service.PhoneInfoService;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("phoneInfoService")
public class PhoneInfoServiceImpl extends ServiceImpl<PhoneLocalDao, TbTelnumInfoEntity> implements PhoneInfoService {

     @Autowired
     private SysUserService sysUserService;
     @Autowired
     private EmployeeInfoService employeeInfoService;


}
