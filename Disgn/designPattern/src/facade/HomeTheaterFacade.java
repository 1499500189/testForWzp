package facade;

/**
 * @author
 * @date 2021 年 12 月 10 日
 */
public class HomeTheaterFacade {
    private  TheaterLight theaterLight;
    private  Popcorn popcorn;
    private  DVDPlayer dvdPlayer;
    private  Stereo stereo;
    private  Projector projector;
    private  Screen screen;
    public HomeTheaterFacade() {
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
        this.stereo = Stereo.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();

    }
    public  void ready(){
        popcorn.on();
        popcorn.pop();
        screen.on();
        screen.pop();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.pop();
    }
    public  void  play(){
        dvdPlayer.play();
    }
    public  void pause(){
        dvdPlayer.pause();
    }
    public  void end(){
        popcorn.off();
        theaterLight.off();
        screen.off();
        projector.off();
        stereo.off();
        dvdPlayer.off();
    }
}
