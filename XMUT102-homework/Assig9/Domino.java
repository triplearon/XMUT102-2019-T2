// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 9
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;


/** Domino
 * Represents individual Dominos - rectangular tiles with two numbers
 * The constructor will return a new Domino with random numbers (0-6) on it
 * Methods:
 *   draw(double left, double top)  draws the domino at a position
 *   flip() the domino over.
 *   getLeft() and getRight() return the numbers on the left/right of the domino
 *   
 */

public class Domino{

    private int first;
    private int secnd;
    private boolean flipped = false;

    public static final int HEIGHT = 50;       // the height of the domino 
    public static final int WIDTH = HEIGHT*2;  // the width of the domino 
    public static final int DIAM = HEIGHT/6;   // the diameter of the spots

    /** Construct a new Domino object with a pair of random values on it */
    public Domino(){
        this.first = (int)(Math.random()*7); // between 0 and 6 inclusive
        this.secnd = (int)(Math.random()*7); // between 0 and 6 inclusive
    }

    /**
     * Turns the domino over
     */
    public void flipNums(){
        this.flipped = !this.flipped;
    }

    /**
     * Return the left number on the Domino
     */
    public int getLeftNum(){
        if (this.flipped) {
            return this.secnd;
        }
        else {
            return this.first;
        }
    }

    /**
     * Return the right number on the Domino
     */
    public int getRightNum(){
        if (this.flipped) {
            return this.first;
        }
        else {
            return this.secnd;
        }
    }

    /**
     * Draws the domino at the position x, y
     */
    public void draw(double x, double y){
        UI.setLineWidth(1);
        UI.setColor(Color.black);
        UI.fillRect(x, y, WIDTH, HEIGHT);
        UI.setColor(Color.red.darker());
        UI.drawRect(x+1, y+1, WIDTH-3, HEIGHT-3);
        UI.setLineWidth(2);
        UI.setColor(Color.gray);
        UI.drawLine(x+HEIGHT, y+2, x+HEIGHT, y+HEIGHT-3);
        UI.setLineWidth(1);
        if (!flipped){
            this.drawNumber(this.first, x, y);
            this.drawNumber(this.secnd, x+HEIGHT, y);
        }
        else {
            this.drawNumber(this.secnd, x, y);
            this.drawNumber(this.first, x+HEIGHT, y);
        }
    }

    /**
     * Draw the number in the square at x,y, using white circles
     */
    public void drawNumber(int num, double x, double y){
        double xOff = x-DIAM/2;  // offset by radius of spots
        double yOff = y-DIAM/2;  // offset by radius of spots
        double left  = xOff+HEIGHT*0.25;
        double centr = xOff+HEIGHT*0.5;
        double right = xOff+HEIGHT*0.75;
        double top = yOff+HEIGHT*0.25;
        double mid = yOff+HEIGHT*0.5;
        double bot = yOff+HEIGHT*0.75;
        UI.setColor(Color.white);
        if (num%2 == 1){   // 1, 3, 5
            UI.fillOval(centr, mid, DIAM, DIAM);
        }
        if (num>1){    // 2, 3, 4, 5, 6
            UI.fillOval(left, top, DIAM, DIAM);
            UI.fillOval(right, bot, DIAM, DIAM);
        }
        if (num>3){ //4, 5, 6
            UI.fillOval(left, bot, DIAM, DIAM);
            UI.fillOval(right, top, DIAM, DIAM);
        }
        if (num==6){
            UI.fillOval(left, mid, DIAM, DIAM);
            UI.fillOval(right, mid, DIAM, DIAM);
        }
    }


}
