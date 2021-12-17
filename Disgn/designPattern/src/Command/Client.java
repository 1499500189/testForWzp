package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
public class Client {
    public static void main(String[] args) {

        //使用命令设计模式 ，完成通过遥控器 ，对电灯的操作
        //创建电灯的对象(接受者)
        LightReceiver lightReceiver =new LightReceiver();
        //创建电灯相关的开关命令
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        //需要一个遥控器
        RemoteController remoteController =new RemoteController();
        //给我们的遥控器设置0
        remoteController.setCommand(0,lightOnCommand,lightOffCommand);
        System.out.println("-----按下灯的开按钮----");
        remoteController.onButtonWasPushed(0);
        System.out.println("+++++按下灯的关的按钮+++");
        remoteController.offButtonWasPushed(0);
        remoteController.undoButtonWasPushed();//撤销最近的一个操作





        System.out.println("使用电控制视机");

        TVReceiver tvReceiver =new TVReceiver();
        TVOnCommand tvOnCommand = new TVOnCommand(tvReceiver);
        TVOffCommand tvOffCommand = new TVOffCommand(tvReceiver);
        remoteController.setCommand(1,tvOnCommand,tvOffCommand);

    }
}
