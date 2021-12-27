package main.java.test.demo.builder;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public abstract class AbstractHouse {
    //打地基
    //砌墙
    //封顶
    public abstract  void buildBasic();
    public  abstract  void buildWalls();
    public  abstract  void roofed();
    public  void build(){
        buildBasic();
        buildWalls();
        roofed();

    }
}
