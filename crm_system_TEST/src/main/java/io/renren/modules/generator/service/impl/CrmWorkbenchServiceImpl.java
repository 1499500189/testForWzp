package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.modules.employee.constant.ConstantRoles;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.generator.dao.po.GraphicalStatisticsVo;
import io.renren.modules.generator.util.ExportExcelListener;
import io.renren.modules.generator.entity.dto.ExcelSummaryStatisticsDto;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmCategoryService;
import io.renren.modules.generator.service.CrmProjectService;
import io.renren.modules.milliondataexport.exportexcel.RunThread;
import io.renren.modules.springdatatest.hello.CrmCategory;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CrmWorkbenchDao;
import io.renren.modules.generator.entity.CrmWorkbenchEntity;
import io.renren.modules.generator.service.CrmWorkbenchService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;


@Service("crmWorkbenchService")
public class CrmWorkbenchServiceImpl extends ServiceImpl<CrmWorkbenchDao, CrmWorkbenchEntity> implements CrmWorkbenchService {
    @Autowired
    private CrmCategoryService crmCategoryService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CrmProjectService crmProjectService;
    @Autowired
    private EmployeeInfoService employeeInfoService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private CrmWorkbenchDao crmWorkbenchDao;
    protected Logger log = LoggerFactory.getLogger(getClass());
    private static final Integer sheetTotal = 65535;
    private static final BlockingQueue BLOCKING_QUEUE = new ArrayBlockingQueue(8);

    private static final ThreadPoolExecutor.CallerRunsPolicy POLICY = new ThreadPoolExecutor.CallerRunsPolicy();
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, BLOCKING_QUEUE, POLICY);

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmWorkbenchEntity> page = this.page(
                new Query<CrmWorkbenchEntity>().getPage(params),
                new QueryWrapper<CrmWorkbenchEntity>()
        );
        return new PageUtils(page);
    }
    @Override
    public IPage<CrmWorkbenchEntity> selectWorkbenchList(Map<String, Object> params, SysUserEntity currentUser) {
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
        Page<CrmWorkbenchEntity> iPage = new Page<>(page, limit);
        return null;
    }

    @Override
    public CrmWorkbenchEntity getWorkbench(Long id) {
return  null;
    }

    @Override
    public IPage<ChartVo> getSummaryList(Map<String, Object> params, SysUserEntity currentUser) {
        //分页参数
        long curPage = 1;
        long limit = 10;
        if ((String) params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }
        Page<CrmProjectEntity> iPage = new Page<>(curPage, limit);
        IPage<ChartVo> c = baseMapper.getSummaryList(iPage, params);

        return c;
    }
    @Override
    public List<ExcelWorkbenchDto> getSummaryList(Page<ChartVo> chartVoPage, Map<String, Object> params) {

        IPage<ExcelWorkbenchDto> c = baseMapper.selectWorkbenchList(chartVoPage, params);
        List<ExcelWorkbenchDto> records = c.getRecords();
     //   ArrayList<ExcelWorkbenchDto> excelWorkbenchDtos = new ArrayList<>();

       /* records.forEach(e -> {
            ExcelWorkbenchDto excelWorkbenchDto = new ExcelWorkbenchDto();
            BeanUtils.copyProperties(e, excelWorkbenchDto);
            excelWorkbenchDtos.add(excelWorkbenchDto);
        });*/
        return records;
    }

    @Override
    public List<ExcelWorkbenchDto> selectWorkbenchListPageLimit(Integer i, Integer i1, Integer iAll, Map<String, Object> params) {
        List<ExcelWorkbenchDto> excelWorkbenchDtos = baseMapper.selectWorkbenchListPageLimit(i, i1,iAll,params);
        return excelWorkbenchDtos;
    }





    @Override
    public List<ChartVo> exportExcel(SysUserEntity user, Map<String, Object> params,Integer integer) {



        //导出所有，使用不分页的方法
        Page<CrmWorkbenchEntity> objectPage = new Page<>(integer, 100000);
        IPage<ChartVo> summaryList = baseMapper.getSummaryList(objectPage, params);
        //创建ExcelDictDTO列表，将Dict列表转换成ExcelDictDTO列表
        ArrayList<ExcelWorkbenchDto> excelDictDTOList = new ArrayList<>(summaryList.getRecords().size());
   /*     records.forEach(c -> {
            ExcelWorkbenchDto excelTransactionRecordDTO = new ExcelWorkbenchDto();
            BeanUtils.copyProperties(c, excelTransactionRecordDTO);
            excelDictDTOList.add(excelTransactionRecordDTO);
        });*/
        return summaryList.getRecords();

        //创建ExcelDictDTO列表，将Dict列表转换成ExcelDictDTO列表
   //     return records;
    }
    //普通版本的
    public void exportExcelPeople(HttpServletResponse response, Map<String, Object> params) {

        //第几个线程
        long beforeTime = System.currentTimeMillis();
        QueryWrapper<ExcelWorkbenchDto> queryWrapper = new QueryWrapper<>();

        try {
            new ExportExcelListener<ExcelWorkbenchDto>(crmWorkbenchDao).exportExcel(response, "表", ExcelWorkbenchDto.class,
                    queryWrapper, crmWorkbenchDao, params);
        } catch (IOException e) {

            e.printStackTrace();
        }
        long afterTime = System.currentTimeMillis();
        log.error("耗时:{}", afterTime - beforeTime);
    }


    @Override
    //多线程的
    public void exportExcel(HttpServletResponse response, Map<String, Object> params) {
        //第几个线程
        long beforeTime = System.currentTimeMillis();
        //使用for循环
        Long aLong = crmWorkbenchDao.selectColumnCount(params);
        int pageNumber = (int) Math.ceil((double) aLong / (double) 100000);    //分页条数看情况

        for (int i = 1; i <=pageNumber; i++) {


            //GenaratorThread genaratorThread = new GenaratorThread(response, params, crmWorkbenchDao, i);
            try {
              //  FutureTask futureTask = new FutureTask<Callable>(genaratorThread);
                RunThread runThread = new RunThread(response, params, crmWorkbenchDao, i, this);
                new Thread(runThread).start();
                System.out.println("执行完成call方法");
            } catch (Exception e) {
               // e.printStackTrace();
                e.getMessage();
            }

        }
        long afterTime = System.currentTimeMillis();
        log.error("耗时:{}", afterTime - beforeTime);

    }
    public   Integer exportLimit(HttpServletResponse response, Map<String, Object> params,Integer i){
        long beforeTime = System.currentTimeMillis();
        QueryWrapper<ExcelWorkbenchDto> queryWrapper = new QueryWrapper<>();

        try {
            new ExportExcelListener<ExcelWorkbenchDto>(crmWorkbenchDao).exportExcel(response, i+"", ExcelWorkbenchDto.class,
                    queryWrapper, crmWorkbenchDao, params, i);
        } catch (IOException e) {

            e.printStackTrace();
        }
        long afterTime = System.currentTimeMillis();
        log.error("耗时:{}", afterTime - beforeTime);
        return  i;
    }

    @Override
    public List<ExcelSummaryStatisticsDto> exportSummaryStatisticsExcel(SysUserEntity user, Map<String, Object> params) {
        //导出所有，使用不分页的方法
        List<ChartVo> records = baseMapper.getSummaryListNoPage(params);
        //创建ExcelDictDTO列表，将Dict列表转换成ExcelDictDTO列表
        List<ExcelSummaryStatisticsDto> excelSummaryStatisticsDtos = new ArrayList<>(records.size());
        records.forEach(c -> {
            ExcelSummaryStatisticsDto excelTransactionRecordDTO = new ExcelSummaryStatisticsDto();
            BeanUtils.copyProperties(c, excelTransactionRecordDTO);
            excelSummaryStatisticsDtos.add(excelTransactionRecordDTO);
        });
        return excelSummaryStatisticsDtos;
    }

    @Override
    public void updateWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity currentUser) {
    }

    @Transactional
    public void doSomething(CrmWorkbenchEntity crmWorkbench, SysUserEntity user) {
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert() {
    }


    @Override
    public void saveWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity user) {
    }



    class PoiDataCallable implements Callable<List<ExcelWorkbenchDto>> {
        private Integer pageNo; //dangqian页 从0开始
        private String search;

        public PoiDataCallable(Integer pageNo, String search) {
            this.pageNo = pageNo;
            this.search = search;
        }

        @Override
        public List<ExcelWorkbenchDto> call() throws Exception {
            log.info("----当前线程：{}", Thread.currentThread().getName());

            long startTime = System.currentTimeMillis();
            List<ExcelWorkbenchDto> exifInfoList = null;

            //  exifInfoList = crmWorkbenchService.getSummaryList(chartVoPage,params);
            exifInfoList  = selectWorkbenchListPageLimit(pageNo*100000, 100000,pageNo*100000*100000,new HashMap<>());
            long endTime=System.currentTimeMillis();
            long spendTime=endTime-startTime;
            log.info(Thread.currentThread().getName()+"查询耗时："+spendTime);
            //查询数据  ，
            return exifInfoList;
        }
    }
    /**
     * 获取导出Excel需要的数据-easyExcel
     * @param search
     * @return
     * @throws Exception
     */
    public List<List<ExcelWorkbenchDto>> getDataV2(final String search)throws Exception {
        List<List<ExcelWorkbenchDto>> resList = Lists.newLinkedList();

        Long total = getBaseMapper().selectColumnCount(new HashMap<>());
        if (total > sheetTotal){
            Long count = (total % sheetTotal == 0) ? total / sheetTotal : total / sheetTotal + 1;
            List<PoiDataCallable> calls = Lists.newLinkedList();
            for (int i = 0; i <= count; i++) {
                calls.add(new PoiDataCallable(i,search));
            }
            List<Future<List<ExcelWorkbenchDto>>> list = EXECUTOR.invokeAll(calls);
            for (Future<List<ExcelWorkbenchDto>> future:list) {
                resList.add(future.get());
            }
        }else {
            return  null;
        }
        return resList;
    }

    @Override
    public List<GraphicalStatisticsVo> getGraphicStatisticsList(Map<String, Object> params, SysUserEntity user) {
        List<GraphicalStatisticsVo> c =  baseMapper.getGraphicStatisticsList(params);
        return c;
    }

     @Override
     @Transactional()
    public void saveWorkbenchEntity() {
        CrmWorkbenchEntity crmWorkbenchEntity = new CrmWorkbenchEntity();
        crmWorkbenchEntity.setId(1001L);
        crmWorkbenchEntity.setTelephone("111");
        baseMapper.insert(crmWorkbenchEntity);
        crmCategoryService.saveWork();
      int i=1/0;
    }
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public void  saveWork(){
        CrmCategoryEntity categoryEntity = new CrmCategoryEntity();
        categoryEntity.setCategoryName("dsa");
        crmCategoryService.save(categoryEntity);
        int i =1/0 ;
    }


}
