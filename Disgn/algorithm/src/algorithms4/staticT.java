package algorithms4;

public class staticT {
    public static void main(String[] args) {

        double sqrt = sqrt(-1);
        System.out.println(sqrt);
    }

    //计算平方根的方法(牛顿迭代法)
    public static  double sqrt(double c){

        if (c<0){
            return  Double.NaN ;
        }
        double  err = 1e-15;
        double t =c;
        while (Math.abs(t-c/t)>err*t){

            t = (c/t+t)/2.0 ;
        }
        return t ;
    }
}
