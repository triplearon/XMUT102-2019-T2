import edu.princeton.cs.algs4.StdRandom;

public class T15 {
    public static int[] histogram(int[] a, int M){
        int[] ans=new int[M];
        int count=0;
        for (int i = 0; i < M ; i++) {
            for (int y:
                 a) {
                if (y == i){
                    count++;
                }

            }

            ans[i]=count;
            count=0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a= new int[50];

        for (int i = 0; i <a.length ; i++) {
            a[i]=StdRandom.uniform(10);
            System.out.print(a[i]+" ");
        }
        System.out.println();

        int[] b=T15.histogram(a,3);
        for (int c:
             b) {
            System.out.print(c+" ");
        }
    }
}
