// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 6
 * Name:1814209208
 * Username:xmut_1812409208
 * ID:1812409208
 */

import ecs100.*;
import java.awt.Color;

/** Frog
 *  A new frog starts at the given position, with the given direction, and 
 *     has either a "light" or "dark" shade.
 *  Frogs can turn in 4 directions: left, right, up, and down. 
 *  Frogs move around at a constant speed in an arena with an enclosing wall,
 *     following its direction, until it hits a wall. In which case it stops moving.
 *  Frog can grow in size, and (for the completion) can also shrink by resetting their size
 *      to the orginal value.
 *
 *  The walls of the arena are determined by the constants:
 *    FrogGame.TOP_WALL, FrogGame.BOT_WALL, FrogGame.LEFT_WALL and FrogGame.RIGHT_WALL
 */

public class Frog {
    // Constants
    public static final int INITIAL_SIZE = 30;
    public static final int GROWTH_RATE = 3;
    public static final int SPEED = 2;

    // Fields

    protected String dir;
    protected double x;
    protected double y;
    protected String shade;
    protected int size;
    public int flyCount=0;

    //Constructor 
    /** 
     * Make a new frog
     * The parameters specify the initial position of the top left corner,
     *   the direction, and the shade of the Frog ("light" or "dark")
     * We assume that the position is within the boundaries of the arena
     */
    public Frog(double left, double top, String dir, String shade)  {
        
        this.x=left;
        this.y=top;
        size=INITIAL_SIZE;
        this.dir=dir;
        this.shade=shade;
       
    }

    /**
     * Turn right  (don't redraw)
     */
    public void turnRight(){
        dir="Right";

    }

    /**
     * Turn left  (don't redraw)
     */
    public void turnLeft(){
        dir="Left";

    }

    /**
     * Turn up  (don't redraw)
     */
    public void turnUp(){
        dir="Up";

    }

    /**
     * Turn down  (don't redraw)
     */
    public void turnDown(){
        dir="Down";

    }

    /**
     * Moves the frog: 
     *   use SPEED unit forward in the correct direction
     *   by changing the position of the frog.
     * Make sure that the frog does not go outside the arena, by making sure 
     *  - the top of the frog is never smaller than FrogGame.TOP_WALL
     *  - the bottom of the frog is never greater than FrogGame.BOT_WALL
     *  - the left of the frog is never smaller than FrogGame.LEFT_WALL
     *  - the right of the frog is never greater than FrogGame.RIGHT_WALL
     *  DO NOT REDRAW THE FROG!!!
     */
    public void move() {
        switch(dir){
            case "Up": 
            if(y-size/2.0>FrogGame.TOP_WALL+2){y-=SPEED;}
            break;
            
            case "Down":
            if(y+size/2.0<FrogGame.BOT_WALL-2){y+=SPEED;}
            break;
            
            case "Left" : 
            if(x-size/2.0>FrogGame.LEFT_WALL+2){x-=SPEED;}
            break;
            
            case "Right" : 
            if(x+size/2.0<FrogGame.RIGHT_WALL-2){x+=SPEED;}
            break;
        }
    }

    /**
     * Check whether the frog is touching the given point, eg, whether the
     *   given point is included in the area covered by the frog.
     * Return true if the frog is on the top of the position (x, y)
     * Return false otherwise
     */
    public boolean touching(double x, double y) {
        if (x>this.x-size/2.0 && x<this.x+size/2.0 && y>this.y-size/2.0 && y<this.y+size/2.0){
            flyCount++;
            return true;
        }
        return false;
    }


    /**
     * The Frog has just eaten a bug
     * Makes the frog grow larger by GROWTH_RATE.
     */
    public void grow(){
        if(size<90){
        size=INITIAL_SIZE+flyCount*GROWTH_RATE;
      }
    }

    /**
     * The Frog has just bumped into a snake
     * Makes the frog size reset to its original size
     * ONLY NEEDED FOR COMPLETION
     */
    public void shrink(){
        
        flyCount=0; 
        
        this.draw();
    }

    /**
     * Draws the frog at the current position and the current size.
     * Use the correct image file (darkfrog.jpg or lightfrog.jpg)
     */
    public void draw(){
        UI.drawImage(shade+"frog.jpg",x-size/2,y-size/2,size,size);
    }
    
    public double getX(){return x;}
    
    public double getY(){return y;}
    
    
}

