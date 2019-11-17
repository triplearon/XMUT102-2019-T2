import edu.princeton.cs.algs4.StdRandom;


public class T11 {

    public static void main(String[] args) {
        boolean[][] a=new boolean[10][10];
        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                a[i][j]=StdRandom.bernoulli(0.5);
            }
        }

        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10 ; j++) {
                System.out.print("行: "+(i+1)+"列: "+ (j+1));
                if(a[i][j]){
                    System.out.println(" *");
                }else{
                    System.out.println("  ");
                }
            }

        }




    }




}
