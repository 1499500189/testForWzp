package main.java.test.demo.builder.inprove;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class client {
    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        House house = houseDirector.constructHouse();
        System.out.println(house.getBaise());
        System.out.println(house.getRoofed());
        System.out.println(house.getWall());
        System.out.println();
    }
}
