package io.renren.modules.dynamic_object;

import com.qiniu.util.Md5;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.modules.generator.dao.po.GraphicalStatisticsVo;
import io.renren.modules.generator.service.CrmProjectService;
import io.renren.modules.generator.service.CrmWorkbenchService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-15 11:20:46
 */
@RestController
@RequestMapping("/generator/graphicalchart")
@Api(tags = "图形汇总统计")
public class CrmGraphicalChartController extends AbstractController {
    @Autowired
    private CrmWorkbenchService crmWorkbenchService;
    @Autowired
    private CrmProjectService crmProjectService;

    private final String  CGLIB ="$cglib_prop_";


//    /**
//     * 获取到图标的展示列表 老方法，调整业务之前使用
//     */
//    @GetMapping("/list")
//    @RequiresPermissions("generator:chart:list")
//    @ApiOperation("获取到图表信息")
//    public R list(@RequestParam Map<String, Object> params) {
//        SysUserEntity user = getUser();
//
//
//        List<GraphicalStatisticsVo> graphicStatisticsList =  crmWorkbenchService.getGraphicStatisticsList(params,user);
//        //获取到范围内需要展示的项目名称 。 生成的统计表单上面的动态列也是他的顺序 ，位置不变，方便动态生成类
//        List<String> collectProjectName = graphicStatisticsList.stream().map(GraphicalStatisticsVo::getProjectName).distinct().collect(Collectors.toList());
//
//        //通过key value 方便之后拼接数据的时候找到对应的对象
//        Map<String, GraphicalChartVo> stringGraphicalChartVoMap = new HashMap<>();
//        for (String c: collectProjectName) {
//            GraphicalChartVo graphicalChartVo = new GraphicalChartVo();
//            graphicalChartVo.setProjectName(c);
//            stringGraphicalChartVoMap.put(c,graphicalChartVo);
//        }
//        //拼接数据
//        for (GraphicalStatisticsVo c: graphicStatisticsList) {
//            String projectName = c.getProjectName();
//            GraphicalChartVo graphicalChartVo = stringGraphicalChartVoMap.get(projectName);
//            StringBuilder agentBusiness = graphicalChartVo.getAgentBusiness();
//
//            if (c.getUsername()!=null){
//                agentBusiness.append(c.getUsername());
//                agentBusiness.append("  ");
//                agentBusiness.append(c.getCount()+"个;    \n\r");
//            }
//            Integer totalAfter = graphicalChartVo.getTotal()+c.getCount();
//            graphicalChartVo.setTotal(totalAfter);
//        }
//
//        //返回list集合
//        List<GraphicalChartVo> graphicalList = new ArrayList<>();
//        for (Map.Entry<String, GraphicalChartVo> entry : stringGraphicalChartVoMap.entrySet()) {
//            graphicalList.add(entry.getValue());
//        }
//        return R.ok().put("chartList", graphicalList);
//    }
    /**
     * 获取到图标的展示列表 ，新方法，调整业务之后使用
     */
    @GetMapping("/list")
    @RequiresPermissions("generator:chart:list")
    @ApiOperation("获取到图表信息")
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity user = getUser();


        List<GraphicalStatisticsVo> graphicStatisticsList =  crmWorkbenchService.getGraphicStatisticsList(params,user);
        //获取到范围内需要展示的项目名称 。 生成的统计表单上面的动态列也是他的顺序 ，位置不变，方便动态生成类
        List<String> collectProjectName = graphicStatisticsList.stream().map(GraphicalStatisticsVo::getProjectName).distinct().collect(Collectors.toList());
        List<String> usernameAllList = graphicStatisticsList.stream().map(GraphicalStatisticsVo::getUsername).distinct().collect(Collectors.toList());
        //用于动态生成类，需要将上面集合转换成set
        Set<String> projectNameSet = new HashSet<>(collectProjectName);
        HashMap tMap = null;
        try {
            tMap = new ClassUtil().dynamicObject(new GraphicalChartVo(), projectNameSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建map， 存储名字和对应的对象的映射。
        HashMap<String, Object> nameMap = new HashMap<>();

        //给nameMap赋值 ， 顺便吧名字反射进去
        for (int i = 0; i < usernameAllList.size(); i++) {
            DynamicBean dynamicBean = new DynamicBean(tMap);
            //获取到设置的对象
            Object obj= dynamicBean.getObject();
            nameMap.put(usernameAllList.get(i), obj);
            // Md5.md5(usernameAllList.get(i).getBytes()

            String name = CGLIB+ ConstantGraphicalChart.USERNAME;
            try {
                Field fieldUsername = dynamicBean.getObject().getClass().getDeclaredField(name);
                fieldUsername.setAccessible(true);
                fieldUsername.set(obj, (String)usernameAllList.get(i));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for (GraphicalStatisticsVo g: graphicStatisticsList ) {

            Object o = nameMap.get(g.getUsername());
            String username = g.getUsername();
            // String cglibName = "$cglib_prop_" + Md5.md5(g.getUsername().getBytes());
            Double numberAchievements = g.getNumberAchievements();
            Integer count = g.getCount();
            try {
                //总业绩数设置
               Field fieldNumberAchievements = o.getClass().getDeclaredField(CGLIB+ConstantGraphicalChart.NUMBER_ACHIEVEMENTS);
                fieldNumberAchievements.setAccessible(true);
                if (fieldNumberAchievements.get(o)!=null){
                        fieldNumberAchievements.set(o,numberAchievements+(Double) fieldNumberAchievements.get(o));
                }else {
                        fieldNumberAchievements.set(o,numberAchievements);
                }
                //总计设置
                Field fileCount = o.getClass().getDeclaredField(CGLIB+ConstantGraphicalChart.TOTAL);
                fileCount.setAccessible(true);
                if (fileCount.get(o)!=null){
                    fileCount.set(o,count+(Integer)fileCount.get(o));
                }else {
                    fileCount.set(o,count);
                }
                //todo 遍历其他的项目名称，进行设置
                String projectName = g.getProjectName();
                //当前套餐的项目名

                    //md5加密 之后，获取属性防止乱码
                 String  name =CGLIB+ Md5.md5(projectName.getBytes());
                    Field filename = o.getClass().getDeclaredField(name);
                    filename.setAccessible(true);
                    if (filename.get(o)!=null){
                        filename.set(o,(Integer)fileCount.get(o)+1);
                    }else {
                        filename.set(o,1);
                    }
               // }
               // String name = "$cglib_prop_" + Md5.md5(usernameAllList.get(i).getBytes());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //将map集合转换成list
        List<Object> graphicalList = new ArrayList<>();
        //返回list集合
       // List<GraphicalChartVo> graphicalList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : nameMap.entrySet()) {
            graphicalList.add(entry.getValue());
        }

        ArrayList<TableHeadVO> tableHead = new ArrayList<>();
        tableHead.add(new TableHeadVO("username","所属人"));
        for (String e: collectProjectName) {
            TableHeadVO tableHeadVO = new TableHeadVO(Md5.md5(e.getBytes()),e);
            tableHead.add(tableHeadVO);
        }
        tableHead.add(new TableHeadVO("total","总计"));
        tableHead.add(new TableHeadVO("numberAchievements","业绩数"));

        return R.ok().put("chartList",graphicalList).put("tableHead",tableHead);

    }




}
