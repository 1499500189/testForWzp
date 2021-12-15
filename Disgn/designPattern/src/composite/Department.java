package composite;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
//部门是叶子节点，add和remove这两个方法不需要重写了
public class Department extends  OrganizationComponent{
    public Department(String name, String des) {
        super(name, des);
    }
    @Override
    protected void print() {
        System.out.println("叶子节点的输出"+getName());

    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public String getDes() {
        return super.getDes();
    }
}
