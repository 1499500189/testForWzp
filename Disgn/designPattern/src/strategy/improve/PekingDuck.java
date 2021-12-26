package strategy.improve;

public class PekingDuck extends Duck {
    //给他一个不能飞翔的
    public PekingDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("北京鸭——————");
    }

}
