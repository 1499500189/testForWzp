package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.modules.employee.constant.ConstantRoles;
import io.renren.modules.employee.entity.EmployeeInfoEntity;
import io.renren.modules.employee.service.EmployeeInfoService;
import io.renren.modules.generator.util.ExportExcelListener;
import io.renren.modules.generator.entity.dto.ExcelSummaryStatisticsDto;
import io.renren.modules.generator.entity.dto.ExcelWorkbenchDto;
import io.renren.modules.generator.entity.CrmCategoryEntity;
import io.renren.modules.generator.entity.CrmProjectEntity;
import io.renren.modules.generator.entity.vo.ChartVo;
import io.renren.modules.generator.service.CrmCategoryService;
import io.renren.modules.generator.service.CrmProjectService;
import io.renren.modules.milliondataexport.exportexcel.RunThread;
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
/*        IPage<CrmWorkbenchEntity> workbenchList = baseMapper.selectWorkbenchList(iPage, params);
        List<CrmWorkbenchEntity> records = workbenchList.getRecords();
        for (CrmWorkbenchEntity record : records) {
            String telephone = record.getTelephone();
            if (telephone.length() >= 7) {
                record.setTelephone(telephone.substring(0, 3) + "****" + telephone.substring(7));
            }


        }*/

        return null;
    }

    @Override
    public CrmWorkbenchEntity getWorkbench(Long id) {
        CrmWorkbenchEntity crmWorkbenchEntity = baseMapper.selectById(id);
        Long categoryId = crmWorkbenchEntity.getCategoryId();
        QueryWrapper<CrmCategoryEntity> categoryWrapper = new QueryWrapper<>();
        categoryWrapper.eq("category_id", categoryId);
        CrmCategoryEntity categoryEntity = crmCategoryService.getOne(categoryWrapper);
        if (null != categoryEntity) {
            crmWorkbenchEntity.setCategoryName(categoryEntity.getCategoryName());
        }


        Long projectId = crmWorkbenchEntity.getProjectId();
        QueryWrapper<CrmProjectEntity> projectWrapper = new QueryWrapper<>();
        projectWrapper.eq("project_id", projectId);
        CrmProjectEntity projectEntity = crmProjectService.getOne(projectWrapper);
        if (null != projectEntity) {
            crmWorkbenchEntity.setProjectName(projectEntity.getProjectName());
        }

        Long userId = crmWorkbenchEntity.getUserId();
        QueryWrapper<SysUserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("user_id", userId);
        SysUserEntity userEntity = sysUserService.getOne(userWrapper);
        if (null != userEntity) {
            crmWorkbenchEntity.setUsername(userEntity.getUsername());
        }

        String telephone = crmWorkbenchEntity.getTelephone();
        if (telephone.length() >= 7) {
            crmWorkbenchEntity.setTelephone(telephone.substring(0, 3) + "****" + telephone.substring(7));
        }


        return crmWorkbenchEntity;
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
    public List<ExcelWorkbenchDto> selectWorkbenchListPageLimit(Integer i, Integer i1) {
        List<ExcelWorkbenchDto> excelWorkbenchDtos = baseMapper.selectWorkbenchListPageLimit(i, i1);
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
        // log.error(crmWorkbench.toString());
        String roleName = sysUserService.getCurrentUserRoleName(currentUser);
        if (roleName.equals(ConstantRoles.executiveDirector) || roleName.equals(ConstantRoles.staff)) {
            throw new RRException("不能修改", 399);
        }
        //  Long projectId = crmWorkbench.getProjectId();
        //CrmCategoryEntity crmCategoryEntity = crmProjectService.getCrmCategoryEntity(projectId);
        String projectName = crmWorkbench.getProjectName();
        CrmProjectEntity crmProjectEntity = crmProjectService.getCrmProjectEntityByProjectName(projectName);
        CrmCategoryEntity crmCategoryEntity = crmProjectService.getCrmCategoryEntityByProjectName(projectName);
        crmWorkbench.setCategoryId(crmCategoryEntity.getCategoryId());
        crmWorkbench.setProjectId(crmProjectEntity.getProjectId());

        baseMapper.updateById(crmWorkbench);
    }

    @Transactional
    public void doSomething(CrmWorkbenchEntity crmWorkbench, SysUserEntity user) {

        ((CrmWorkbenchService) AopContext.currentProxy()).insert();
        //insert();
        try {
            Thread.sleep(100);
            System.out.println("执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        throw new RRException("ccc", 3003);
        // saveWorkbenchEntity(crmWorkbench,user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert() {
        CrmProjectEntity crmProjectEntity = new CrmProjectEntity();
        crmProjectEntity.setProjectName("测试");
        crmProjectEntity.setAchievements(133D);
        crmProjectEntity.setCategoryId(4L);
        crmProjectEntity.setSortIndex(2);
        crmProjectService.save(crmProjectEntity);
    }


    @Override
    public void saveWorkbenchEntity(CrmWorkbenchEntity crmWorkbench, SysUserEntity user) {


        String projectName = crmWorkbench.getProjectName();
        CrmProjectEntity crmProjectEntity = crmProjectService.getCrmProjectEntityByProjectName(projectName);
        CrmCategoryEntity crmCategoryEntity = crmProjectService.getCrmCategoryEntityByProjectName(projectName);
        crmWorkbench.setCategoryId(crmCategoryEntity.getCategoryId());
        crmWorkbench.setProjectId(crmProjectEntity.getProjectId());

        try {
            baseMapper.insert(crmWorkbench);
        } catch (DataIntegrityViolationException e) {
            throw new RRException("信息字段长度过长", 500);
        }


    }


}
