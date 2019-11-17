import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class T3 {
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();

        while(!StdIn.isEmpty() && list.size()<3){
            list.add(StdIn.readInt());
        }

        if(list.get(0)==list.get(1) && list.get(1)==list.get(2)){
            System.out.println("equal");
        }else{
            System.out.println("not equal");
        }
    }
}
