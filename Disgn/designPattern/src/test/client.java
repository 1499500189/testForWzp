package test;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class client {
    public static void main(String[] args) {
        Map dic = Dic.getDic();
        List<DictionariesVO> ageRange = (List)dic.get("ageRange");
        for ( DictionariesVO d:
             ageRange) {
            System.out.println(d.getValue()+"sdasdad"+d.getLabel());
        }
        System.out.println(ageRange);
        System.out.println(dic);

    }
}
