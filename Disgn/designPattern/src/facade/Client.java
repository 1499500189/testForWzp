package facade;

/**
 * @author
 * @date 2021 年 12 月 10 日
 */
public class Client {
    public static void main(String[] args) {

        HomeTheaterFacade facade =new HomeTheaterFacade();
        facade.ready();
        facade.play();
        facade.pause();
        facade.end();
    }
}
