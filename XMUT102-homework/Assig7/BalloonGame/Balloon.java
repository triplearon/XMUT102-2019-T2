// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 7
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;

/**
 * Represents a balloon that can grow until it bursts.
 * While it is active (before it bursts) it is drawn as a coloured circle.
 * When is is no longer active (after being burst) it is a light grey circle
 */
public class Balloon{
    // Fields
    public static final double INITALRADIUS=10;
    private double radius = 10;
    public double centerX, centerY;
    private Color color;
    private boolean active = true;
    //private boolean isStrip;

    /**
     * Construct a new Balloon object. 
     * Parameters are the coordinates of the center of the balloon 
     */
    public Balloon(double x, double y){
        this.centerX = x;
        this.centerY = y;
        this.color = Color.getHSBColor((float)Math.random(), 1.0f, 1.0f);
        //isStrip= Math.random()-0.2<1E-7; 
    }

    /**
     * Draw the balloon
     */
    public void draw(){
        /*if(this.isStrip){
            if(this.active){
            UI.setColor(color);
            UI.fillArc(centerX,centerY,INITALRADIUS*2,INITALRADIUS*2,0,180);
            UI.fillRect(centerX,centerY,INITALRADIUS*2,radius*2);
            UI.fillArc(centerX,centerY-radius*2+INITALRADIUS,INITALRADIUS*2,INITALRADIUS*2,180,180);
            }
            return;
        }
        */ //I gave up it
        if (this.active){
            UI.setColor(color);
            UI.fillOval(centerX-radius, centerY-radius, radius*2, radius*2);
            UI.setColor(Color.black);
            UI.drawOval(centerX-radius, centerY-radius, radius*2, radius*2);
        }
        else {
            UI.setColor(Color.lightGray);
            UI.fillOval(centerX-radius, centerY-radius, radius*2, radius*2);
        }
    }

    /**
     * Make the balloon larger by a random amount between 4 and 10
     */
    public void expand(){
        if (this.active){
            this.radius = this.radius + (Math.random()*6 + 4);
        }
    }
    
    public void clear(){
            UI.eraseOval(centerX-radius, centerY-radius, radius*2, radius*2);
    }

    /**
     * Returns true if the point (x,y) is on the balloon, and false otherwise
     */
    public boolean on(double x, double y){
        double dx = this.centerX - x;
        double dy = this.centerY - y;
        return ((dx*dx + dy*dy) < (this.radius * this.radius));
    }

    /**
     * Returns true if the balloon is still active (hasn't been burst) and false otherwise
     */
    public boolean isActive(){
        return this.active;
    }

    /**
     * Returns true if this Balloon is touching the other balloon, and false otherwise
     */
    public boolean isTouching(Balloon other){
        double dx = other.centerX - this.centerX;
        double dy = other.centerY - this.centerY;
        double touchingDist = other.radius + this.radius;
        return (Math.hypot(dx,dy) < touchingDist);
    }

    /** Calculates and returns the area of the balloon
     *  Returns it in "centi-pixels" (ie, number of pixels/100)
     *  to keep them in a reasonable range. 
     */
    public int size(){
        return  (int) ((this.radius * this.radius * Math.PI)/100);
    }
    
    public boolean getActive(){
        return this.active;
    }
    
    public double getDist(Balloon ball){
        double dx = ball.centerX - this.centerX;
        double dy = ball.centerY - this.centerY;
        
        return Math.hypot(dx,dy);
    }
    
    

    /** burst the balloon (draws it in gray, and pauses briefly) */
    public void burst(){
        this.active = false;
        this.draw();
        UI.sleep(20);
    }
}
