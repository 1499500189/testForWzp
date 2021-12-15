package bridge;

/**
 * @author
 * @date 2021 年 12 月 06 日
 */
public class UpRightPhone extends  Phone {
    public UpRightPhone(Brand brand) {
        super(brand);
    }
    @Override
    public  void  open(){
        super.open();
        System.out.println("fei折叠方法");

    }

    @Override
    protected void call() {
        super.call();
        System.out.println("fei折叠yang的手机打电话");
    }

    public  void  close(){
        super.close();
        System.out.println("fei折叠yang的手机");

    }

 /*   public  void  call(){
        super.call();
        super.close();


    }*/
}
