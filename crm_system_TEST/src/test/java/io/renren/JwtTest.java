package io.renren;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.dynamic_object.ClassUtil;
import io.renren.modules.dynamic_object.DynamicBean;
import io.renren.modules.dynamic_object.GraphicalChartVo;
import io.renren.modules.generator.dao.PhoneLocalDao;
import io.renren.modules.generator.dao.po.GraphicalStatisticsVo;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.entity.TbTelnumInfoEntity;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.modules.generator.service.PhoneInfoService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.Servlet;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CrmWorkbenchService crmWorkbenchService;
    @Autowired
    private PhoneInfoService phoneInfoService;
    @Test
    @Transactional(readOnly = true)
    public void test() {

    }
    @Test
    public  void  test2(){
        List<GraphicalStatisticsVo> graphicStatisticsList =  crmWorkbenchService.getGraphicStatisticsList(new HashMap<>(),null);
        //获取到范围内需要展示的项目名称 。 生成的统计表单上面的动态列也是他的顺序 ，位置不变，方便动态生成类
        List<String> collectProjectName = graphicStatisticsList.stream().map(GraphicalStatisticsVo::getProjectName).distinct().collect(Collectors.toList());
        List<String> usernameAllList = graphicStatisticsList.stream().map(GraphicalStatisticsVo::getUsername).distinct().collect(Collectors.toList());
        HashMap<String,Object> tMap = null;
        //用于动态生成类，需要将上面集合转换成set
        Set<String> projectNameSet = new HashSet<>(collectProjectName);
        try {
            tMap = new ClassUtil().dynamicObject(new GraphicalChartVo(), projectNameSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Object> entry : tMap.entrySet()) {
            System.out.println(entry);
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }

}
