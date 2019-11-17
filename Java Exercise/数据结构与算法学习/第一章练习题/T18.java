public class T18 {
    public static int mystery(int a, int b){
        if(b == 0) return 0;

        if (b%2 == 0) return mystery(a+a,b/2);

        return mystery(a+a,b/2+a) +a;

    }


    public static int mysteryPlus(int a, int b){
        if(b == 0) return 1;

        if (b%2 == 0) return mysteryPlus(a*a,b/2);

        return mysteryPlus(a*a,b/2*a)+a;

    }

    public static void main(String[] args) {
        System.out.println(mystery(2,25));
        System.out.println(mystery(3,11));
        System.out.println();
        System.out.println(mysteryPlus(2,25));
        System.out.println(mysteryPlus(3,11));
    }
}
