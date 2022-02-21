package algorithms4.class1;

import algorithms4.staticT;

public class test {
    public static long F(int N){
        if (N==0){
            return 0;
        }
        if (N==1){
            return 1;
        }
        return F(N-1)-F(N-2);
    }

    public static void main(String[] args) {
           int a = 99 ;
        System.out.println(a+"s");
        String s = a+"dsa";
        staticT staticT = new staticT();

        System.out.println(staticT.toString());
        System.out.println(staticT+"sda");
        String ss = staticT+"sda";
        System.out.println(ss);

    }
}
