
/* Code for XMUT102 - 2019T2, Assignment 6
 * Name:Deng XinYu
 * Username:xmut_1812409208
 * ID:1812409208
 */

import ecs100.*;
import java.awt.Color;


public class C_Ball{

    public static final double RADIUS = 8;  
    public static final double GROUND = BallGame.GROUND;
    public static final double RIGHT_END = BallGame.RIGHT_END;

    public double x;
    public double height;
    public double stepX;    //Xspeed
    public double stepY; //YSpeed
    public Color colour;
    public boolean isMove=false;
    public boolean isVisible=true;
    public int jumpTime=0;
    // Constructor
  
    public C_Ball(double x, double h){
         this.x=x;
        this.height=h;
        colour=new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
    }
    
    public double getDist(C_Ball o1){
        return Math.hypot(this.x-o1.x,this.height-o1.height);
    }

  
    public void step(){
       if(jumpTime>30){stepY=0;}
        
        x+=stepX;
        height+=stepY;
        
        
        if(GROUND>(height-RADIUS)){
            stepY+=0.38; //I konw that G is 9.8 but if it's 9.8,sometimes when target is too height the ball won't hit it forever!
        }
        
        
        if(GROUND<(height+RADIUS)){
            this.height=GROUND-RADIUS/2+4;
            stepY=-stepY+1.58;
            jumpTime++;
        }
        
        if(!isVisible){
         stepY=0;
         stepX=0;
        }
        
        if(this.x > 650 || this.x<-50){
            isVisible =false;    
        }
    }
    
    
    public double getStepX(){
        return this.stepX;
    }
    
    public double getStepY(){
        return this.stepY;
    }
    
    public void setSpeed(double xSpeed, double ySpeed){
        stepX=xSpeed;
        stepY=ySpeed;
        
    }

    
    public double getHeight(){
        return height;

    }
    
     public void draw(){
         if(isVisible){
             UI.setColor(colour);
        UI.fillOval(x-RADIUS,height-RADIUS*2,RADIUS*2,RADIUS*2);
    }
   }
    
    
    
    public double getX(){
        return x;

    }
}