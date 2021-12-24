package state;

import java.util.Random;

/**
 * ���Գ齱��״̬
 * @author Administrator
 *
 */
//可以抽奖的状态
public class CanRaffleState extends State {

    RaffleActivity activity;

    public CanRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    //已经扣除了积分 ，不能再扣了
    @Override
    public void deductMoney() {
        System.out.println("已经扣去过了积分");
    }

    //可以抽奖了， 抽完奖后，不能在扣了
    @Override
    public boolean raffle() {
        System.out.println("正在抽奖，请稍等!");
        Random r = new Random();
        int num = r.nextInt(10);
        // 10%中奖机会
        if(num == 0){
            // 改变后动状态未发放奖品 context
            activity.setState(activity.getDispenseState());
            return true;
        }else{
            System.out.println("很遗憾没有抽中奖品");
            // 改变状态未不能抽奖
            activity.setState(activity.getNoRafflleState());
            return false;
        }
    }

    //  不能发放奖品
    @Override
    public void dispensePrize() {
        System.out.println("没中奖，不能发放奖品");
    }
}
