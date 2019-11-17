public class T4 {
    public static void main(String[] args) {
        int a = 10, b = 11, c = 12;
        // if (a>b) then c=0; 没有then关键字
        // if a>b {c=0;}; 判断块需要小括号
        if(a>b) c=0;
        if(a>b) c=0; else b=0;
    }
}