package sort;

/**
 * @author
 * @date 2021 年 12 月 27 日
 */
//插入排序
public class InsertSort  {
    public static void main(String[] args) {
        int in ,out ;
        long[] numbs ={1,2,4,3};
        //最外层的遍历，  不能0时开始是因为 第一个可以认为他是有序的 ，
        for (int i = 1; i < numbs.length; i++) {
            long temp  =numbs[i];
             in = i  ;
             //上面先吧值复制一份
             //下面进行交换数的操作，  左边的数小大于他才开始进行交换位置
             while (in>0&&numbs[in-1]>=temp){
                 numbs[in]  =numbs[in-1];
                 --in ;
             }
             numbs[in] =temp;
        }
        System.out.println("--------");
        for (long e: numbs) {
            System.out.println(e);
        }
    }
}
