package algorithms4;

public class MatrixMultiplication {
    public static void main(String[] args) {


    }
    public  static  double[][]  MM(double[][] a  ,double[][] b){
        int N=a.length;
        double[][] c = new double[N][N];
        for (int i = 0; i <N ; i++) {

            for (int j = 0; j <N ; j++) {
                //计算行i和列j的点乘
                for (int k = 0; k <N ; k++) {
                    c[i][j]  = a[i][k]*b[k][j] ;

                }

            }

        }
        return  c;

    }
}
