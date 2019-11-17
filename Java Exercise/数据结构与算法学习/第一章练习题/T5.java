import edu.princeton.cs.algs4.StdRandom;

public class T5 {

    public static void main(String[] args) {
        double a= StdRandom.uniform(2);
        double b= StdRandom.uniform(2);
        int c=0;

        System.out.println(a+" "+b);
        if(a>0&&a<1 && b>0 && b<1){

            System.out.println(true);
        }else{
            System.out.println(false);

        }

    }
}
