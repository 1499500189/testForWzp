package iterator;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class ComputerCollegeIterator implements  Iterator{
   //这里我们需要Department， 使以怎样的方式存放的
    Department[] departments;
    int position = 0 ;//遍历的位置

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }
  //判断是否还有下一个元素
    @Override
    public boolean hasNext() {
        if (position>=departments.length||departments[position]==null){
            return  false;
        }else {
            return  true;
        }

    }

    @Override
    public Object next() {
        Department department =departments[position];
        position+=1;
        return department;
    }
    //删除的方法不需要 ，进行空实现
    public  void  remove(){

    }
}
