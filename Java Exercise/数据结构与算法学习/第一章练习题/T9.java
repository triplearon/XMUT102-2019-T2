import edu.princeton.cs.algs4.StdIn;

public class T9 {
    public static void main(String[] args) {
        int N= StdIn.readInt();
        String s="";
        for (int n = N/2; n >0 ; n/=2) {
            s=(n%2) + s;
        }

        System.out.println(s);
    }
}
//一个转换二进制的方法 虽然Java自带转换成二进制的方法