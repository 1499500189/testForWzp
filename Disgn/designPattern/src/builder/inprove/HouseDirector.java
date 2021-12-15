package builder.inprove;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class HouseDirector {
    HouseBuilder houseBuilder =null;
    //通过构造器传入他 ，或者通过setter方法去传入他

    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    } //指挥者 ，
    public  House constructHouse(){
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        houseBuilder.house.setBaise("111");
        houseBuilder.house.setRoofed("222");
        return houseBuilder.house;
    }
}
