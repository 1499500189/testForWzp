package composite;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Client {
    public static void main(String[] args) {

        //从大到小出创建对象
        OrganizationComponent university = new University("清华大学", "中国顶级大学");
        OrganizationComponent computerCollege=  new College("计算机学院","计算机学院");

        OrganizationComponent infoEngineerCollege=  new College("信息学院","信息");

        university.add(new Department("软件工程","软件工程不从"));
        university.add(new Department("网络工程","网络工程不错"));
        university.add(new Department("计算机科学于技术","计算机科学技术是老潘的专题页"));
        Department department = new Department("1", "2");

        infoEngineerCollege.add(new Department("通信工程","通信不太行"));
        infoEngineerCollege.add(new Department("电子信息工程","nodo页"));
        university.add(computerCollege);
        university.add(infoEngineerCollege);

        university.print();


    }
}
