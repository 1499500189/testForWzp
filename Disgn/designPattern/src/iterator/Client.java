package iterator;

import java.util.ArrayList;

/**
 * @author
 * @date 2021 年 12 月 21 日
 */
public class Client {
    public static void main(String[] args) {
         //创建学院
        ArrayList<College> colleges = new ArrayList<>();

        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();
        colleges.add(computerCollege);
        colleges.add(infoCollege);
        OutPutImpl outPut = new OutPutImpl(colleges);
        outPut.printCollege();
    }
}
