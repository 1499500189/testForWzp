package strategy.improve;

public class test
{
    public static void main(String[] args) {


        FlyBehavior flyBehavior = new FlyBehavior(){
            @Override
            public void fly() {
                System.out.println("sss");
            }
        };
        flyBehavior.fly();
        PekingDuck pekingDuck = new PekingDuck();
        pekingDuck.fly();
        //可动态的改变某个行为 ‘
        pekingDuck.setFlyBehavior(flyBehavior);
        pekingDuck.fly();
    }
}
