public class T14 {
    public static int lg(int N){
        int n=0;
        for (int i = 1; i <N ; i*=2) {
            n++;
        }
        return n-1;
    }

    public static void main(String[] args) {
        System.out.println(lg(666666));
    }
}
