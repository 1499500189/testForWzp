package builder.inprove;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public  abstract class HouseBuilder {
    protected  House  house =new House();
    //将建造的流程写好，抽象的方法
    public  abstract  void buildBasic();
    public  abstract  void buildWalls();
    public  abstract  void roofed();
    //建造房子 ，之后把房子返回去 ，
    public  House buildHouse(){
        return  house;
    }



}
