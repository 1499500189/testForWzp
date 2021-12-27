package com.wzp.user.benmon.builder.inprove;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class HighBuilding extends  HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("高楼的地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼的");
    }

    @Override
    public void roofed() {
        System.out.println("高楼的封顶");
    }
}
