package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Dic {
    private Dic c = new Dic();
     private static  Map<String,List<DictionariesVO>> listMap = new HashMap<>();

     static {
         List<DictionariesVO> sex = new ArrayList<>();
         sex.add(new DictionariesVO("1", "男"));
         sex.add(new DictionariesVO("2", "女"));

         List<DictionariesVO> ageRange = new ArrayList<>();
         ageRange.add(new DictionariesVO("10", "30以下"));
         ageRange.add(new DictionariesVO("15", "30~35"));
         ageRange.add(new DictionariesVO("20", "36~40"));
         ageRange.add(new DictionariesVO("25", "41~45"));
         ageRange.add(new DictionariesVO("30", "46~50"));
         ageRange.add(new DictionariesVO("35", "50以上"));

         List<DictionariesVO> location = new ArrayList<>();
         location.add(new DictionariesVO("11", "武昌区"));
         location.add(new DictionariesVO("13", "洪山区"));
         location.add(new DictionariesVO("15", "汉阳区"));
         location.add(new DictionariesVO("17", "江夏区"));
         location.add(new DictionariesVO("19", "蔡甸区"));
         location.add(new DictionariesVO("21", "江岸区"));
         location.add(new DictionariesVO("23", "江汉区"));
         location.add(new DictionariesVO("25", "硚口区"));
         location.add(new DictionariesVO("27", "青山区"));
         location.add(new DictionariesVO("29", "东西湖区"));
         location.add(new DictionariesVO("31", "汉南区"));
         location.add(new DictionariesVO("33", "黄坡区"));
         location.add(new DictionariesVO("35", "新洲区"));
         List<DictionariesVO> phoneStatus = new ArrayList<>();
         phoneStatus.add(new DictionariesVO("10", "新下发"));
         phoneStatus.add(new DictionariesVO("15", "已查看"));
         phoneStatus.add(new DictionariesVO("17", "企业用户"));
         phoneStatus.add(new DictionariesVO("20", "空号"));
         phoneStatus.add(new DictionariesVO("25", "停机"));
         phoneStatus.add(new DictionariesVO("28", "待开发"));
         phoneStatus.add(new DictionariesVO("30", "未接通"));
         phoneStatus.add(new DictionariesVO("32", "同意添加"));
         phoneStatus.add(new DictionariesVO("35", "添加已通过"));
         phoneStatus.add(new DictionariesVO("36", "拒绝添加请求"));
         phoneStatus.add(new DictionariesVO("37", "不需要"));
         phoneStatus.add(new DictionariesVO("40", "黑名单"));

         List<DictionariesVO> customerClassification = new ArrayList<>();
         customerClassification.add(new DictionariesVO("A", "A"));
         customerClassification.add(new DictionariesVO("B", "B"));
         customerClassification.add(new DictionariesVO("B-", "B-"));
         customerClassification.add(new DictionariesVO("C", "C"));
         customerClassification.add(new DictionariesVO("D", "D"));
         customerClassification.add(new DictionariesVO("E", "E"));
         List<DictionariesVO> share = new ArrayList<>();
         share.add(new DictionariesVO("0", "不分享"));
   /*     share.add(new DictionariesVO("1","小组公海"));
        share.add(new DictionariesVO("2","部门公海"));*/
         share.add(new DictionariesVO("3", "公司公海"));
         List<DictionariesVO> isShare = new ArrayList<>();
         isShare.add(new DictionariesVO("0", "不分享"));
         isShare.add(new DictionariesVO("3", "公司公海"));

         List<DictionariesVO> operator = new ArrayList<>();
         operator.add(new DictionariesVO("1", "移动"));
         operator.add(new DictionariesVO("3", "电信"));
         operator.add(new DictionariesVO("2", "联通"));
         List<DictionariesVO> isDeal = new ArrayList<>();
         isDeal.add(new DictionariesVO("1", "已成交"));
         isDeal.add(new DictionariesVO("0", "未成交"));
         List<DictionariesVO> dealItems = new ArrayList<>();
         dealItems.add(new DictionariesVO("15", "移动"));
         dealItems.add(new DictionariesVO("10", "电信"));
         dealItems.add(new DictionariesVO("20", "联通"));
         List<DictionariesVO> registrationTime = new ArrayList<>();
         registrationTime.add(new DictionariesVO("1", "登记时间排序"));
         registrationTime.add(new DictionariesVO("2", "更新时间排序"));
         List<DictionariesVO> education = new ArrayList<>();
         education.add(new DictionariesVO("10", "初中及以下"));
         education.add(new DictionariesVO("15", "高中"));
         education.add(new DictionariesVO("20", "中专"));
         education.add(new DictionariesVO("25", "高专"));
         education.add(new DictionariesVO("30", "专科"));
         education.add(new DictionariesVO("35", "本科"));
         education.add(new DictionariesVO("40", "硕士"));
         education.add(new DictionariesVO("45", "博士及以上"));
         List<DictionariesVO> isNcnt = new ArrayList<>();
         isNcnt.add(new DictionariesVO("1", "是携号转网"));
         isNcnt.add(new DictionariesVO("0", "不是携号转网"));

        // Map<String, List> listMap = new HashMap<>();
         listMap.put("sex", sex);
         listMap.put("ageRange", ageRange);
         listMap.put("location", location);
         listMap.put("phoneStatus", phoneStatus);
         listMap.put("share", share);
         listMap.put("customerClassification", customerClassification);
         listMap.put("operator", operator);
         listMap.put("isDeal", isDeal);
         listMap.put("dealItems", dealItems);
         listMap.put("registrationTime", registrationTime);
         listMap.put("isShare", isShare);
         listMap.put("education", education);
         listMap.put("isNcnt", isNcnt);
     }

    private Dic() {
    }

    public static Map getDic(){

        return listMap;
    }
}
