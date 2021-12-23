package mediator.smarthouse;

/**
 * @author
 * @date 2021 年 12 月 22 日
 */
public class Curtains extends Colleague{
    public Curtains(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void SendMessage(int stateChange) {

    }

    @Override
    public void StartCoffee() {

    }

    @Override
    public void StopTV() {

    }

    @Override
    public void UpCurtains() {

    }
}
