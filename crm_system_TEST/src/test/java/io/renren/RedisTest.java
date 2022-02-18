package io.renren;

import io.renren.common.utils.RedisUtils;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private CrmWorkbenchService crmWorkbenchService;

	@Test
	public void contextLoads() {
		SysUserEntity user = new SysUserEntity();
		user.setEmail("qqq@qq.com");
		redisUtils.set("user", user);

		System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("user", SysUserEntity.class)));
	}

	@Test
	public void  test3(){
		CrmWorkbenchEntity crmWorkbench = new CrmWorkbenchEntity();
		crmWorkbench.setCreateTime(new Date());
		crmWorkbench.setUserId(1L);
		crmWorkbench.setIsTrue("1");
		crmWorkbench.setProjectId(1L);
		crmWorkbench.setTelephone("33444555667");
		crmWorkbench.setCategoryId(2L);
		crmWorkbench.setPackageAmount(1D);
		crmWorkbench.setReducedAmount(1D);
		crmWorkbench.setNumberAchievements(1D);
		crmWorkbench.setTotalPoints(34D);
		ArrayList<CrmWorkbenchEntity> crmWorkbenchEntities = new ArrayList<>();
		//crmWorkbenchService.save(crmWorkbench);
		for(int i = 0 ;i<1000000;i++) {
			Date date = new Date(1000*i);
			crmWorkbench.setCreateTime(date);
			crmWorkbench.setProcessingDate(date);
			crmWorkbenchEntities.add(crmWorkbench);
		}
		crmWorkbenchService.saveBatch(crmWorkbenchEntities);
	}

}
