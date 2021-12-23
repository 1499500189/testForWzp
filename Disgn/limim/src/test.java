/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public class test {

    //冒泡排序
    public static void main(String[] args) {

      //使用选择排序
         int[]  sums = {55,1,2,33,45};
      //    int min = sums[0];
        for (int i = 0;i< sums.length;i++){
            int min = i;
            for (int j = i+1; j <sums.length ; j++) {
                 if (sums[min]>sums[j]){
                    min  = j ;
                 }
            }

           int temp=  sums[min] ;
            sums[min] =   sums[i];
            sums[i] =temp;
        }
        System.out.println(sums);
        for (int i = 0; i < sums.length; i++) {
            System.out.println(sums[i]);

        }
    }

}
