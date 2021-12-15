package facade;

/**
 * @author
 * @date 2021 年 12 月 10 日
 */
public class Popcorn {
    private  static  Popcorn instance  =new Popcorn();

    public  static  Popcorn getInstance(){
        return  instance;
    }
    public  void  on(){
        System.out.println("pop on");
    }
    public void  off(){
        System.out.println("pop off");
    }
    public  void  pop(){
        System.out.println("pop pop");
    }
    private  Popcorn(){
    }
}
