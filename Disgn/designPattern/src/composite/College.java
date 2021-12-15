package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class College extends  OrganizationComponent{
    List<OrganizationComponent> organizationComponents =new ArrayList<>();
    //实际的业务中 colleage的add和universi的add方法可能不是相同的
    public College(String name, String des) {
        super(name, des);
    }
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }
    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }
    @Override
    protected void print() {
        //print方法就是输出university包含的学院
        System.out.println("_____"+getName()+"_____________");
        //遍历这个集合
        for (OrganizationComponent o:organizationComponents) {
            o.print();
        }
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getDes() {
        return super.getDes();
    }

    @Override
    public void setDes(String des) {
        super.setDes(des);
    }

}
