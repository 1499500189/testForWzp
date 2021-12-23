package iterator;

import java.util.List;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class InfoCollegeIterator implements  Iterator{
    List<Department> departmentList ; //信息工程学院是以List方式存放系
    int index =-1;//索引

    public InfoCollegeIterator(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public boolean hasNext() {
        if (index>=departmentList.size()-1){
            return false;
        }else {
            index +=1;
            return  true;
        }
    }

    @Override
    public Object next() {
        return departmentList.get(index);
    }
    //空实现 删除方法
    public  void remove(){

    }
}
