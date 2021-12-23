package iterator;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class ComputerCollege implements  College{
    Department[]   departments;
    int numOfDepartment  = 0 ;//保存当前数组的对象个数

    public ComputerCollege() {
        departments = new Department[5];
        addDepartment("java专业","Java专业特");
        addDepartment("php","ppp");
        addDepartment("大数据专业","dsj");
    }

    @Override
    public String getName() {
        return "计算机学院";

    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departments[numOfDepartment]   = department;
        numOfDepartment+=1 ;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}
