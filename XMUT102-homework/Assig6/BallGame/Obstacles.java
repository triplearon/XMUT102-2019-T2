import ecs100.*;
import java.awt.*;

public class Obstacles
{
    private double x;
    private double y;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 4;
    public Color coloru=Color.black;
    
    private double stepX;
    private double stepY;
    
    boolean isMove =false;
    boolean isVisible =true;
    
       public Obstacles(double x,double y){
        this.x=x-8;
        this.y=y+10;
        UI.setColor(coloru);
        UI.fillRect(x,y,WIDTH,HEIGHT);
     } 
    
    public void draw(){
        if(isVisible){
            UI.setColor(Color.black);
        UI.fillRect(x,y,WIDTH,HEIGHT);
     }
    }
    
    public void step(){
        x+=stepX;
        y+=stepY;
        
        stepY+=0.98;
        
        if(x>=600){
            isVisible =false;
        }
        
        if(!isVisible && x>=650){
            stepX=0;
            stepY=0;
        }
        
        if(y+HEIGHT > 450){
            y=450-HEIGHT;
            stepY=-0.6*stepY+1;
        }
    }
    
    public void setSpeed(double x,double y){
        stepX=x;
        stepY=y;
    }
    
    public double getDist(C_Ball b1){
        if(b1.getHeight()<y){
            
        if(b1.getX()>=x && b1.getX()<=x+WIDTH){
           return (y-b1.getHeight());
        }else{
           return Math.hypot(b1.getX()-x+8,b1.getHeight()-y+2);           
        }
        
    }else{
        if(b1.getX()>=x && b1.getX()<=x+WIDTH){
           return (b1.getHeight()-y+HEIGHT);
        }else{
           return Math.hypot(b1.getX()-x+8,y-2+HEIGHT-b1.getHeight());
        }
    }
}

    public double getDist(Obstacles o1){
        return Math.hypot(o1.x-x,o1.y-y);
    }


    public double getStepY(){  return this.stepY;}
    public double getStepX(){  return this.stepX;}
}
