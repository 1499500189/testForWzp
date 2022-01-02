package sort;

/**
 * @author
 * @date 2021 年 12 月 27 日
 */
public class ChoiceSort {
    public static void main(String[] args) {
        int[] ints = {2,5,44,667,1,3,898};
        int count  = 0 ;
        //选择排序
        for  ( int i =count ; i < ints.length; i++) {
            int   min  = i;
            for (int j =i+1 ; j<ints.length;j++){
                if (ints[j]<ints[min]){
                    min = j ;
                }
            }
            int flag  =  ints[min];
            ints[min] = ints[i];
            ints[i] =flag;
        }
        for (int i = 0; i <ints.length ; i++) {
            System.out.println(ints[i]);

        }
    }
}
