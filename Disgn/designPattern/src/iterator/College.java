package iterator;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public interface College {
    public  String getName();
    //增加系的方法
    public  void addDepartment(String name, String desc);
    //返回一个迭代器 ，遍历
    public  Iterator createIterator();
}
