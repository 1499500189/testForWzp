package iterator;

import java.util.List;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class OutPutImpl {
    //学院集合
    List<College> collegeList;
    public OutPutImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }
   //遍历所有的学院 ，然后调用printDepartment 输出各个学院的系
    public  void printCollege(){
        //java 的list集合有迭代器接口 ，
        java.util.Iterator<College> iterator = collegeList.iterator();
        while (iterator.hasNext()){
            College college =iterator.next();

            //得到对应的迭代器
            printDepartment(college.createIterator());
            System.out.println("学院的名称");
        }

    }


    //输出  学院输出系的问题
    public  void printDepartment(Iterator iterator){
        while (iterator.hasNext()){
            Department next = (Department) iterator.next();
            System.out.println(next.getName());
        }
    }
}
