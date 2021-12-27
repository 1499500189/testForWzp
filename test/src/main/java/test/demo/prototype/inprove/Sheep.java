package test.demo.prototype.inprove;

/**
 * @author
 * @date 2021 年 12 月 02 日
 */
public class Sheep implements  Cloneable {
    private  String  name;
    private int age;
    private  String color;

    public Sheep getFriend() {
        return friend;
    }

    public void setFriend(Sheep friend) {
        this.friend = friend;
    }

    private String address =  "蒙古草原";
    private  Sheep friend ;    //🐏的朋友
    //克隆该实例，使用默认的clone方法来完成
    @Override
    protected Object clone()   {

        Sheep sheep =null;
        try {
            sheep =  (Sheep)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheep;
    }
    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
