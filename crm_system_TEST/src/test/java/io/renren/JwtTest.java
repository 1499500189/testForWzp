package io.renren;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CrmWorkbenchService crmWorkbenchService;
    @Test
    @Transactional(readOnly = true)
    public void test() {
        CrmWorkbenchEntity byId = crmWorkbenchService.getById(1);
        System.out.println(byId);
        CrmWorkbenchEntity crmWorkbenchEntity = new CrmWorkbenchEntity();
        crmWorkbenchEntity.setTelephone("100100");
        crmWorkbenchEntity.setId(1L);
        crmWorkbenchService.updateById(crmWorkbenchEntity);

        CrmWorkbenchEntity byId1 = crmWorkbenchService.getById(1);
        System.out.println(byId1);


    }

}
