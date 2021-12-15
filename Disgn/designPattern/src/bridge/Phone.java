package bridge;

/**
 * @author
 * @date 2021 年 12 月 06 日
 */
public abstract class Phone {


    //组合品牌
    private  Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }
    protected  void   open(){
        this.brand.open();
    }
    protected  void   close(){
        this.brand.close();
    }
    protected  void  call(){
        this.brand.call();
    }
}
