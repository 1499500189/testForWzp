package com.wzp.user.benmon.builder.inprove;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class CommonHouse extends  HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通房子砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("普通房子的屋顶");
    }

}
