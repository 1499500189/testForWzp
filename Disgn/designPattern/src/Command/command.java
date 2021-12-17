package Command;

/**
 * @author
 * @date 2021 年 12 月 17 日
 */
//创建命令接口
public interface command {
   //执行动作（操作）
   void    execute();
   //撤销动作（操作）
   void undo();

}
