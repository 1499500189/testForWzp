package facade;

/**
 * @author
 * @date 2021 年 12 月 10 日
 */
public class Projector {
    private  static  Projector instance= new Projector();


    public  static  Projector getInstance(){
        return  instance;
    }
    public  void  on(){
        System.out.println("Projector on");
    }
    public void  off(){
        System.out.println("Projector off");
    }
    public  void  pop(){
        System.out.println("Projector pop");
    }
    private  Projector(){
    }

}
