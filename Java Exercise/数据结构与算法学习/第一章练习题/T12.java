public class T12 {
    public static void main(String[] args) {
        int[] a=new int[10];
        for (int i = 0; i < 10 ; i++) {
            a[i]=9-i;
        }

        for (int i = 0; i < 10 ; i++) {
            a[i]=a[a[i]];
        }

        for (int i = 0; i < 10 ; i++) {
            System.out.println(a[i]);
        }
    }
}
//莫名其妙的一些数组操作 貌似可以从中间截断数组然后倒顺走回去