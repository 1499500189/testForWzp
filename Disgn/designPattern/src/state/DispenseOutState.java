package state;

/**
 *
 * @author Administrator
 *
 */
//奖品发放状态
    //说明 ，当我们activity改变成DispenseOutState，抽奖活动结束
public class DispenseOutState extends State {

	// 初始化时传入后动引用
    RaffleActivity activity;

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }
    @Override
    public void deductMoney() {
        System.out.println("奖品发送完了 ， 请下次再参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发送完了。请下次再参加");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品发送玩了，请下次再参加");
    }
}
