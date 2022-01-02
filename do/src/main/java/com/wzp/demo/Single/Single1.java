package com.wzp.demo.Single;

/**
 * @author
 * @date 2021 年 10 月 29 日
 */
public class Single1 {
    private  final static    Single1 s  ;
    static {
        s       =   new Single1();
    }
    private Single1 (){
    }


     public static Single1 getInstance(){

        return  s;
     }
}
class Single2 {
    private   static    Single2 s  ;
    private Single2 (){
    }


    public static Single2 getInstance(){
          if (s==null){
              s  =new Single2();
          }
        return  s;
    }
}

class test{

    public static void main(String[] args) {

        Single1 instance = Single1.getInstance();
        System.out.println(instance);
        Single1 instance1 = Single1.getInstance();
        System.out.println(instance1);
    }
}
class Single3 {
    private   static volatile    Single3 s  ;
    private Single3 (){
    }
    public static Single3 getInstance(){
        if (s==null){
            synchronized (Single3.class){
                if (s==null){
                    s  =new Single3();
                }
            }
        }
        return  s;
    }
}
class Single4 {
    private Single4 (){
    }
    private  static   class  inner{
        private  final static       Single4 s        =new Single4();
    }
    public static Single4 getInstance(){
        return  inner.s;
    }
}

enum Single5{
    InSTANCE ;
    public  void sayOk(){
        System.out.println("ok");
    }
}
