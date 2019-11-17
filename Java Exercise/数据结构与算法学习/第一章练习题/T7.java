import edu.princeton.cs.algs4.StdOut;

public class T7 {
    public static void a(){
        double t= 9.0;
        while (Math.abs(t-9.0/t)>.001)
            t = (9.0 / t + t) / 2.0;

            StdOut.printf("%.5f\n",t);
    }

    public static void b(){
        int sum=0;
        for (int i = 1; i < 1000; i++)
            for (int j = 0; j < i; j++)
                    sum++;


        StdOut.println(sum);
    }

    public static void c(){
        int sum=0;
        for (int i = 1; i < 1000 ; i*=2)
            for (int j = 0; j < 1000 ; j++)
                    sum++;



            StdOut.println(sum);
    }

    public static void main(String[] args) {
        T7.a();
        T7.b();
        T7.c();
        System.out.println((char)('a'+4));
        System.out.println('b'+'c');



    }
}
