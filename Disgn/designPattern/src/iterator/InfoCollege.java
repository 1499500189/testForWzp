package iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class  InfoCollege implements  College{
    List<Department> departmentList   ;
    public InfoCollege() {
       departmentList  =new ArrayList<>();
       addDepartment("信息安全专业","信息");
       addDepartment("网络安全专业","网络");
    }


    @Override
    public String getName() {
        return "信息工程专业";
    }

    @Override
    public void addDepartment(String name, String desc) {
       Department department= new Department(name, desc);
       departmentList.add(department);
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
