package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
public class RemoteController {
     //开 按钮的命令数组
   command[] onCommands;
   command[] offCommands;
   command undoCommand; //执行撤销的命令

//构造器 ，完成对按钮的初始化
   public RemoteController() {
      onCommands = new command[5];
      offCommands = new command[5];
      for (int i = 0; i < 5; i++) {
         onCommands[i] = new NoCommand();
         offCommands[i] = new NoCommand();
      }
   }
   public void  setCommand(int no,command oncommand ,command offcommand){
   // 给我们的按钮设置你需要的命令
      onCommands[no] =oncommand;
      offCommands[no] =offcommand;
   }
   //按下开按钮
   public  void  onButtonWasPushed(int no){  //如果no时 0，处理的就是第一排的打开的按钮
      //找到你按下的开的按钮 ，并调用对应的方法
      //今天认识到了正式项目和之前联系的虚拟项目的区别，感觉差异较大，项目的结构差别很大 ，之前在课程的学习中，都是对于知识点的单独学习，知识点并没有很好的连接成面，课程中的项目处于精简的状态，功能较单一，导致看到真实项目时没有思绪，真实项目的文件也很多，每个模块负责各自的功能，各组件之间相互配合，相互调用，比较难理解
      onCommands[no].execute();
      //记录这个操作 ，可以进行撤销操作
       undoCommand =  onCommands[no];
   }
   //按下关按钮
   public  void  offButtonWasPushed(int no){  //如果no时 0，处理的就是第一排的打开的按钮
      //找到你按下的开的按钮 ，并调用对应的方法
      //今天认识到了正式项目和之前联系的虚拟项目的区别，感觉差异较大，项目的结构差别很大 ，之前在课程的学习中，都是对于知识点的单独学习，知识点并没有很好的连接成面，课程中的项目处于精简的状态，功能较单一，导致看到真实项目时没有思绪，真实项目的文件也很多，每个模块负责各自的功能，各组件之间相互配合，相互调用，比较难理解
      offCommands[no].execute();
      //记录这个操作 ，可以进行撤销操作
      undoCommand =  offCommands[no];
   }
   //按下撤销按钮
   public  void  undoButtonWasPushed(){  //如果no时 0，处理的就是第一排的打开的按钮
      //找到你按下的开的按钮 ，并调用对应的方法
      //今天认识到了正式项目和之前联系的虚拟项目的区别，感觉差异较大，项目的结构差别很大 ，之前在课程的学习中，都是对于知识点的单独学习，知识点并没有很好的连接成面，课程中的项目处于精简的状态，功能较单一，导致看到真实项目时没有思绪，真实项目的文件也很多，每个模块负责各自的功能，各组件之间相互配合，相互调用，比较难理解
      //记录这个操作 ，可以进行撤销操作
      undoCommand.undo();
   }


}
