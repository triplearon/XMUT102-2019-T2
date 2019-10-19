//Assig5的Challenge部分 非Challenge部分以及涉及到Core和Com部分的代码已删除
//主要分享如何实现评论消除器 本次作业难度个人觉得仅难于Ass3的 那个我都懒得传
//xmut_1812409208发于2019/10/14 21:08


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

public class ImageRenderer{
    public static final int TOP = 20;   // top edge of the image
    public static final int LEFT = 20;  // left edge of the image
    public static final int PIXEL_SIZE = 2;  


    
    public void renderImageChallenge(){
      try{
        Scanner sc=new Scanner(new File(UIFileChooser.open()));
        Image a=new Image();
        a.garbageClear(sc);
        switch(a.kind){
            case "P2" : a.drawP2(); break;
            case "P3" : a.drawP3(); a.drawImage(); break;
        }
    }catch(IOException e){}
}
    class Image{
        String kind;
        int Width;
        int cols;
        int rows;
        Scanner scan;
        //Inner class 内部类 其实只是想对speic Width cols rows做个引用 但是不知道除了内建一个类还有什么好的办法 学业不精.
        public void garbageClear(Scanner sc){  //垃圾评论清理器 以#为断开 清理掉任何以#开头的无用信息
            kind=sc.next();
            scan=sc;
            
            String mark=sc.next();
            while(mark.contains("#")){
            sc.nextLine();
            mark=sc.next();
            }
            cols=Integer.parseInt(mark);
            
            mark=sc.next();
            while(mark.contains("#")){
            sc.nextLine();
            mark=sc.next();
            }
            rows=Integer.parseInt(mark);
            
            mark=sc.next();
            while(mark.contains("#")){
            sc.nextLine();
            mark=sc.next();
            }
            Width=Integer.parseInt(mark);
            
           // UI.printf("speic:%s Width:%d cols:%d rows:%d",speic,Width,cols,rows);  
        }
        
        public void drawP2(){
         //画灰阶图 略
        }
        
        public void drawP3(){
        //画普通图 略
        }
        
        public void drawImage(){
        //画动画 略
        }
    
    }
        }
}
  


}
