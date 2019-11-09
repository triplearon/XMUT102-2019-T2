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

/** Ball represents a ball that is launched by the mouse towards a direction.
 *    Each time the step() method is called, it will take one step.  
 * For the Completion part, gravity should act on the ball by reducing its vertical speed.
 */

public class Ball{

    public static final double RADIUS = 8;  // radius of the balls.
    public static final double GROUND = BallGame.GROUND;
    public static final double RIGHT_END = BallGame.RIGHT_END;

    // Fields to store
    //   the state of the ball:  x, height, stepX, stepY, colour
    //   other constants for the ball: size, position of the ground
    //   The ball should initially be not moving at all.
    public double x;
    public double height;
    public double stepX;
    public double stepY;
    public Color colour;
    // Constructor
    /** Construct a new Ball object.
     *  Parameters are the initial position (x and the height above the ground),
     *  Stores the parameters into fields 
     *  and initialises the colour
     */
    public Ball(double x, double h){
        this.x=x;
        this.height=h;
        colour=new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
    }

    // Methods
    
    public double getStepX(){
        return this.stepX;
    }
    
    public double getStepY(){
        return this.stepY;
    }
    /**
     * Draw the ball on the Graphics Pane in its current position
     * (unless it is past the RIGHT_END )
     */
    public void draw(){
        UI.setColor(this.colour);
        UI.fillOval(x-RADIUS,height-RADIUS,RADIUS*2,RADIUS*2);
    }

    /**
     * Move the ball one step.
     * Core:
     *    Change its height and x position using the vertical and horizonal steps
     * Completion:
     *    Reduce its vertical speed each step (due to gravity), 
     *    If it would go below ground, then change its y position to ground level and
     *      set the  vertical speed back to 0.
     */
    public void step(){
        x+=stepX;
        height+=stepY;
        
        
        
        if(GROUND>(height+RADIUS)){
            stepY+=0.38;
        }
        
        if((height+RADIUS)>GROUND){
            this.height=GROUND-RADIUS;
            stepY=-stepY+1;
            
        }
    }

    /**
     * Set the speed of the ball.
     * The horizontal speed is how much it moves to the right in each step.
     * The vertical speed is how much it moves up in each step (negative if ball going down).
     */
    public void setSpeed(double xSpeed, double ySpeed){
        stepX=xSpeed;
        stepY=ySpeed;
        
    }

    /**
     * Return the height of the ball above the ground
     */
    public double getHeight(){
        return height;

    }

    /**
     * Return the horizontal position of the ball
     */
    public double getX(){
        return x;

    }


}
