package io.renren.modules;

/**
 * @author
 * @date 2022 年 02 月 10 日
 */
public class Employee extends  Person{
    Person _person  =new Employee();
    public  String getName(){
        return _name;
    }
    public void setName(String arg){
        _person.setName(arg);
    }
    public String toString(){
        return "Emp: "+_person.getLastName();
    }
}
class Person{
    String _name;
    public  String getName(){
        return _name;
    }
    public void setName(String arg){
        _name=arg;
    }
    public String getLastName(){
        return _name.substring(_name.lastIndexOf(' ')+1);
    }
}
class  test{
    public static void main(String[] args) {
        Employee employee = new Employee();
        String name = employee.getName();
        System.out.println(name);
    }
}
