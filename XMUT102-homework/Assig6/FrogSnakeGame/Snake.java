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
    public static double speed;
    private Frog target;
    public double x;
    public double y;
    
    
      //部分成员变量略
    
    //constructor
    public Snake(){
        //构造器略
    }
    
    
    //method
    public void setSpeed(int count){
        this.speed=1.0+count*SPEED_RATE; //用于动态改变蛇的速度 蛇的速度会随着苍蝇吃的多少而改变
    }
    
    //
    //get set方法略
    //
    
    public void move(){
       //略
    }
    
    public void chaseForg(){
        double disX=target.getX()-this.x;
        double disY=target.getY()-this.y;
        double d=this.getDist(target.getX(),target.getY());
        
        dir=Math.atan(disY/Math.abs(disX));
        if(disX>0){isStepXPositive=true;}
        
        if(disX<0){isStepXPositive=false;}
        //追踪方法 先求目标（青蛙）与蛇之间的x距离 y距离 然后用比例求出反tan的值 再将得到的角度设置为蛇的dir
        // 由于tan在14和23象限值一样，用一个变量控制蛇的x方向
    }
    
    public void draw(){
        //略
    }
    
    public double getDist(double x,double y){
        return Math.hypot(this.x-x,this.y-y); //获取点到蛇的距离
    }
    //private
    private boolean wallTouching(){
        
        
        //撞墙检测 已过时 追踪青蛙永远不会撞墙
    }
    
    private static double dirRandom(){
        return Math.random()*360;  //随机生成0到360 已过时
    }
    
    private static double positionRandom(){
        return 65+Math.random()*350; //随机生产x y位置
    }
    
    
}
