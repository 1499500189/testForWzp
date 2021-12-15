package facade;

/**
 * @author
 * @date 2021 年 12 月 10 日
 */
public class Stereo {
    private  static  Stereo instance= new Stereo();


    public  static  Stereo getInstance(){
        return  instance;
    }
    public  void  on(){
        System.out.println("Stereo on");
    }
    public void  off(){
        System.out.println("Stereo off");
    }
    public  void  pop(){
        System.out.println("Stereo pop");
    }
    private  Stereo(){
    }
}
