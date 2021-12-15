package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
//university就是composite ，可以管理college
public class University extends  OrganizationComponent{
    List<OrganizationComponent> organizationComponents =new ArrayList<>();
    public University(String name, String des) {
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
