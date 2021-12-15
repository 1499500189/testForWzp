package flyweight;

/**
 * @author
 * @date 2021 年 12 月 14 日
 */
public class User {
    private  String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
