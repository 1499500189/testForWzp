package strategy.improve;

public class ToyDuck extends Duck {


    public ToyDuck() {
        flyBehavior =new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("玩具鸭");
    }
    //玩具鸭重写父类的所有方法

    @Override
    public void quack() {

        System.out.println("玩具鸭子不能叫");
    }

    @Override
    public void swim() {
        System.out.println("玩具鸭子不会游泳");
    }


}
