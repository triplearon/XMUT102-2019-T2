import edu.princeton.cs.algs4.StdOut;

class Fibonacci {
    /*
    public static long F(int N){
        if (N == 0) return 0;
        if (N == 1) return 1;

        return F(N-1) +F(N-2);
    }
    */


    public static long F(int N){
        long f = 0;
        long g = 1;

        for(int i=0; i<=N ;i++){

            f = f+g;
            g = f-g;
        }
        return g;
    }

    public static void main(String[] args) {
        for (int N=0; N<100 ;N++){
            StdOut.println(N +" " +F(N));
        }
    }
}
//递归要很久才能输出100次结果 而用for循环在一瞬间进行了100次的斐波那契数列输出