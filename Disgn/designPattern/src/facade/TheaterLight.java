package facade;

/**
 * @author
 * @date 2021 年 12 月 10 日
 */
public class TheaterLight {
    private  static  TheaterLight instance= new TheaterLight();


    public  static  TheaterLight getInstance(){
        return  instance;
    }
    public  void  on(){
        System.out.println("TheaterLight on");
    }
    public void  off(){
        System.out.println("TheaterLight off");
    }
    public  void  pop(){
        System.out.println("TheaterLight pop");
    }
    private  TheaterLight(){
    }
}
