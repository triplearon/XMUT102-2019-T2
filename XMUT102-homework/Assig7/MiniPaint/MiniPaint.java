
import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class MiniPaint{
    
    protected String shape="Line";
    protected boolean isFill=false;

    
     //略
    
    /** Set up the GUI */
    public void setupGUI(){
        //UI略
    }

    public void clearGraphics(){
        UI.clearGraphics();
      //略
    }
    
    public void doSetLine(){
        //略
    }
    
 

    /** Respond to the Rect button */
    public void doSetRect(){
       //略
    }

    /** Respond to the Oval button */
    public void doSetOval(){
        //略
    }

    /** Respond to the Image button */
    public void doSetImage(){
         //略

    /** Respond to the Colour button */
    public void doSetColour(){
        colour=JColorChooser.showDialog(null,"Choose one color",Color.black); //颜色设置
        //略
    }

    /** Respond to the Fill/Nofill button */
    public void doSetFill(){
        if(isFill){
        isFill=false;
       
        }else{
        isFill=true;
    
        }//填充色开关
       //略
    }

    public void doMouse(String action, double x, double y) {
         //第一点记录略
        
        if(action.equals("released")){
            switch(shape){
                case "Line":this.drawALine(x,y); break;
                case "Rect":this.drawARectangle(x,y);break;
                case "Oval":this.drawAnOval(x,y);break;
                case "Image": this.drawAnImage(x,y);break;
                default :UI.println("Please choose a pattern");
          }
      }
    }

        
    public void drawALine(double x, double y){
        //画线方法略 太简单了这都不知道不如重学
    }

   
    public void drawARectangle(double x, double y){
       //间距设置略
        if(!isFill){
        
        if(width>0 && height>0) {UI.drawRect(xPressed,yPressed,width,height);}
        
        if(width>0 && height<0) {UI.drawRect(xPressed,y,width,Math.abs(height));}
        
        if(width<0 && height>0) {UI.drawRect(x,yPressed,Math.abs(width),height);}
        
        if(width<0 && height<0) {UI.drawRect(x,y,Math.abs(width),Math.abs(height));}
     } else{
         
         if(width>0 && height>0) {UI.fillRect(xPressed,yPressed,width,height);}
        
        if(width>0 && height<0) {UI.fillRect(xPressed,y,width,Math.abs(height));}
        
        if(width<0 && height>0) {UI.fillRect(x,yPressed,Math.abs(width),height);}
        
        if(width<0 && height<0) {UI.fillRect(x,y,Math.abs(width),Math.abs(height));}
        }
        
        //以上对应四向画矩形
    }
    

    public void drawAnOval(double x, double y){
       //同画矩形 略
    }

 
    public void drawAnImage(double x, double y){
        //同画矩形 略
    }
    
    protected void showCondition(){
        //不用println函数展示当前画笔状态方法 机密
}
    
    // Main:  constructs a new MiniPaint object and call the setupGUI method
    public static void main(String[] arguments){
        MiniPaint mp = new MiniPaint();
        mp.showCondition();
        mp.setupGUI();

    }   

}
