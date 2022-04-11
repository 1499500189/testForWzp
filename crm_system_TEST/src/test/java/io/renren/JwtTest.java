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
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.entity.CrmWorkloadEntity;
import io.renren.modules.generator.entity.TbTelnumInfoEntity;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmCategoryService;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.modules.generator.service.CrmWorkloadService;
import io.renren.modules.generator.service.PhoneInfoService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.Servlet;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CrmWorkbenchService crmWorkbenchService;
    @Autowired
    private CrmWorkloadService crmWorkloadService;
    @Autowired
    private PhoneInfoService phoneInfoService;
    @Autowired
    private CrmCategoryService crmCategoryService;
    @Test
    public  void  test2(){

                //just方法直接声明
                Flux.just(1,2,3,4);
                Mono.just(1);
                //其他的方法
                Integer[] array = {1,2,3,4};
                Flux.fromArray(array);

                List<Integer> list = Arrays.asList(array);
                Stream<Integer> stream = list.stream();
                Flux.fromStream(stream);
    }

}
