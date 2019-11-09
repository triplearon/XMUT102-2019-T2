// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 6
 * Name:Deng XinYu
 * Username:xmut_1812409208
 * ID:1812409208
 */

import ecs100.*;
import java.awt.Color;

/** The snake is created at a random position with a random 360 degree direction.
* The constructor does not have any parameters.
 * It can move
 *  - makes it go forward one step in its current direction.
 *  - if outside arena boundaries, makes it go backward one step, and
 *         then turn to a new (random) direction.
 *  The walls of the arena are determined by the constants:
 *    FrogSnakeGame.TopWall, FrogSnakeGame.BotWall, FrogSnakeGame.LeftWall
 *    and FrogSnakeGame.RightWall
 * It can report its current position (x and y) with the
 *  getX() and getY() methods.
 *  move() will make it move in the direction it is going. 
 *  draw() will make it draw itself (image size should be 30).
 */

public class Snake{
    private static final int SIZE=30;
    private static final double SPEED_RATE=0.02; 
    public  static final double RAD=57.2958;
    private static double speed=1.0;
    private double x;
    private double y;
    private double dir;
    private boolean isStepXPositive;
    
    public Frog target;
    
    //constructor
    public Snake(){
        x=this.positionRandom();
        y=this.positionRandom();
        dir=this.dirRandom();
    }
    
    
    //method
    public void setSpeed(int count){
        this.speed=1.0+count*SPEED_RATE;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void move(){
        if(wallTouching()){dir=this.dirRandom();}
        
        if(isStepXPositive){
        x+=speed*Math.cos(dir);
    }else{x-=speed*Math.cos(dir);}
        y+=speed*Math.sin(dir);
    }
    
    public void chaseForg(){
        double disX=target.getX()-this.x;
        double disY=target.getY()-this.y;
        double d=this.getDist(target.getX(),target.getY());
        
        dir=Math.atan(disY/Math.abs(disX));
        if(disX>0){isStepXPositive=true;}
        
        if(disX<0){isStepXPositive=false;}
        
    }
    
    public void draw(){
        UI.drawImage("snake.jpg",x-SIZE/2.0,y-SIZE/2.0,SIZE,SIZE);
    }
    
    public double getDist(double x,double y){
        return Math.hypot(this.x-x,this.y-y);
    }
    //private
    private boolean wallTouching(){
        return x+SIZE/2.0>=FrogSnakeGame.RIGHT_WALL||
               y-SIZE/2.0<=FrogSnakeGame.TOP_WALL  ||
               x-SIZE/2.0<=FrogSnakeGame.LEFT_WALL ||
               y+SIZE/2.0>=FrogSnakeGame.BOT_WALL ;
    }
    
    private static double dirRandom(){
        return Math.random()*360;
    }
    
    private static double positionRandom(){
        return 65+Math.random()*350;
    }
    
    
}
