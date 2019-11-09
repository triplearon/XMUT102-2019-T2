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

/** FrogGame: Core
 *  Game with two frogs that race to get to the bug first in order to eat it.
 *  (uses keys: 
 *        player 1: W/A/S/D for up/left/down/right; controls the "light" frog
 *        player 2: I/J/K/L for up/left/down/right; controls the "dark" frog
 *  Frogs move around at a constant speed in an arena with an enclosing wall.
 *  When one of the frog reaches the bug, the frog eats it (grows in size),
 *       and a new bug appears at a random position within the arena walls.
 *
 *  Controls:
 *  - key to start (space)
 *  - keys to turn the two frogs  (w/a/s/d  and i/j/k/l)
 *
 *  Display:
 *   program constantly shows
 *   - the arena, the bug and the frogs
 *
 *  Constants:
 *    This class should contain constants specifying the various parameters of
 *    the game, including the geometry of the arena and obstacle.
 */

public class FrogGame{

    // Constants for the Geometry of the game.
    // (You may change or add to these if you wish)

    public static final int ARENA_SIZE = 400;
    public static final int LEFT_WALL = 30;
    public static final int RIGHT_WALL = LEFT_WALL+ARENA_SIZE;
    public static final int TOP_WALL = 50;
    public static final int BOT_WALL = TOP_WALL+ARENA_SIZE;
    public static final int BUG_SIZE = 15;

    public static final int DELAY = 20;  // milliseconds to delay each step.

    // Fields to store the two frogs 
    private Frog frog1;
    private Frog frog2;
    private AIFrog frogAI;
    private ScoreBoard SB=new ScoreBoard();

    // Fields to position of the centre of the bug
    private double xBug;
    private double yBug;

    private boolean isPlayWithPC=false;
    public int Socre1;
    public int Socre2;
    /** 
     * Set up the GUI
     */
    public void setupGUI(){
        UI.setImmediateRepaint(false);
        UI.setKeyListener(this::doKey);
        UI.addButton("Single",()->this.modeChoose(true));
        UI.addButton("Multiplayer",()->this.modeChoose(false));
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0);
    }
    
    private void modeChoose(boolean a){
        this.isPlayWithPC=a;
    }

    /**
     * Respond to keys.
     * the space key should reset the game to have two new frogs
     * the w/a/s/d keys should make the light frog turn up, left, down or right
     * the i/j/k/l keys should make the dark frog turn up, left, down or right
     */
    public void doKey(String key){
        if (key.equals("p")|| key.equals(" ")||key.equals("Space")) {   //When I press space, the key pass the value by chinese,So I use "p" to start
            restartGame();
        }
        else if (this.frog1 == null || this.frog2 == null){
            UI.printMessage("Move mouse to arena and press Space to start");
        }
        else if (key.equals("w") ){
            this.frog1.turnUp();
        }
        else if (key.equals("a") ){
            this.frog1.turnLeft();
        }
        else if (key.equals("s") ){
            this.frog1.turnDown();
        }
        else if (key.equals("d") ){
            this.frog1.turnRight();
        }
        else if (key.equals("i") ){
            this.frog2.turnUp();
        }
        else if (key.equals("j") ){
            this.frog2.turnLeft();
        }
        else if (key.equals("k") ){
            this.frog2.turnDown();
        }
        else if (key.equals("l") ){
            this.frog2.turnRight();
        }
    }

    /**
     * Reset the game with two new frogs in the starting positions.
     * Loop forever
     *  - move each frog one step,
     *  - if any frog has caught the bug, it grows, and a new bug appears somewhere else.
     *  - redraw the game (frogs, arena, and bug)
     */
    private void restartGame(){
        Socre1=0;
        Socre2=0;
        if(!isPlayWithPC){
        this.multiPlay();
        }else{
        this.singlePlay();
        }

    }
    private void singlePlay(){
       
        this.frog1 = null;
        this.frog2 = null;
        UI.sleep(3*DELAY);
        this.frog2=new Frog(0,0,"Up","dark");
        // puts a bug at a random position
        this.newBug();

        // make new frogs and call redraw 
        this.frog1 = new Frog(LEFT_WALL+30, (TOP_WALL+BOT_WALL)/2, "Up", "light");
        this.frogAI = new AIFrog(RIGHT_WALL-30, (TOP_WALL+BOT_WALL)/2, "Left", "dark");
        this.frogAI.setBugLocation(xBug,yBug);
        this.redrawAI();
        UI.printMessage("Control the direction of the frogs with keys: a s w d for the lighter frog");

        // Run the game
        while (this.frog1!=null && this.frogAI!=null) {
            frogAI.setSpeed(frog1);
            this.frog1.move();
            this.frogAI.move();
            if (this.frog1.touching(this.xBug, this.yBug)) {
                this.frog1.grow();
                this.newBug();
                this.frogAI.setBugLocation(xBug,yBug);
                frogAI.setSpeed(frog1);
                Socre1=frog1.flyCount;
            }
            else if (this.frogAI.touching(this.xBug, this.yBug)) {
                this.frogAI.grow();
                this.newBug();
                this.frogAI.setBugLocation(xBug,yBug);
                Socre2=frogAI.flyCount;
            }
            this.redrawAI();
            if(Socre1==15 || Socre2==15){
                UI.setFontSize(40);
              if(Socre1>Socre2){
                  
                  UI.drawImage("lightfrog.jpg",250,250,100,100);
                  UI.drawString("Win. You beat a computer!",380,300);
                }else{
                    UI.drawImage("darkfrog.jpg",250,250,100,100);
                  UI.drawString("Win. AI is stronger than human",380,300);
                }
                UI.sleep(10000);
                UI.setFontSize(50);
                this.restartGame();
            }
            UI.sleep(DELAY);
        }
    
    
    }
    
    private void multiPlay(){
        // make sure that the current game ends (if it is running)
        this.frog1 = null;
        this.frog2 = null;
        UI.sleep(3*DELAY);

        // puts a bug at a random position
        this.newBug();

        // make new frogs and call redraw 
        this.frog1 = new Frog(LEFT_WALL+30, (TOP_WALL+BOT_WALL)/2, "Up", "light");
        this.frog2 = new Frog(RIGHT_WALL-30, (TOP_WALL+BOT_WALL)/2, "Left", "dark");
        this.redraw();
        UI.printMessage("Control the direction of the frogs with keys: a s w d for the lighter frog and j k i l for the darker frog");

        // Run the game
        while (this.frog1!=null && this.frog2!=null) {
            this.frog1.move();
            this.frog2.move();
            if (this.frog1.touching(this.xBug, this.yBug)) {
                this.frog1.grow();
                this.newBug();
                Socre1=frog1.flyCount;
            }
            else if (this.frog2.touching(this.xBug, this.yBug)) {
                this.frog2.grow();
                this.newBug();
                Socre2=frog2.flyCount;
            }
            this.redraw();
            if(Socre1==20 || Socre2==20){
                UI.setFontSize(40);
              if(Socre1>Socre2){
                  
                  UI.drawImage("lightfrog.jpg",250,250,100,100);
                  UI.drawString("Win.",380,300);
                }else{
                    UI.drawImage("darkfrog.jpg",250,250,100,100);
                  UI.drawString("Win.",380,300);
                }
                UI.sleep(10000);
                UI.setFontSize(50);
                this.restartGame();
            }
            UI.sleep(DELAY);
        }
    }

    /**
     * Redraws
     * - the arena
     * - the bug
     * - the two frogs
     */
    private void redraw(){
        UI.clearGraphics();
        this.drawArenaBug();
        this.frog1.draw();
        this.frog2.draw();
        SB.drawScoreBoard(Socre1,Socre2);
        UI.repaintGraphics();
    }
    
    private void redrawAI(){
        UI.clearGraphics();
        this.drawArenaBug();
        this.frog1.draw();
        this.frogAI.draw();
        SB.drawScoreBoard(Socre1,Socre2);
        UI.repaintGraphics();
    }

    /**
     * Draw the arena as a rectangle with a bug in it
     */
    private void drawArenaBug(){
        UI.setColor(Color.black);
        UI.setLineWidth(2);
        UI.drawRect(LEFT_WALL, TOP_WALL, ARENA_SIZE, ARENA_SIZE);
        UI.setLineWidth(1);
        UI.drawImage("bug.png", this.xBug-BUG_SIZE/2, this.yBug-BUG_SIZE/2, BUG_SIZE, BUG_SIZE);
    }

    /**
     * Defines new position for the bug and draws it
     */
    private void newBug(){
        this.xBug = LEFT_WALL + BUG_SIZE/2 + Math.random()*(ARENA_SIZE-BUG_SIZE/2);
        this.yBug = TOP_WALL + BUG_SIZE/2 + Math.random()*(ARENA_SIZE-BUG_SIZE/2);
    }
    
    private void drawSocreBoard(){
        UI.setLineWidth(2);
        UI.drawRect(460,50,300,100);
        UI.drawLine(460,80,760,80);
        UI.drawLine(510,50,510,150);
        UI.drawLine(635,50,635,150);
        UI.drawImage("lightfrog.jpg",557.5,52,26,26);
        UI.drawImage("darkfrog.jpg",682.5,52,26,26);
        UI.setFontSize(50);
        UI.drawString(""+Socre1,557.5,140);
        UI.drawString(""+Socre2,682.5,140);
        
      }

    /**
     * Create a new FrogGame object, setup the GUI, and draw the arena and the bug
     * 
     */
    public static void main(String[] arguments){
        
        
        FrogGame fg= new FrogGame();
        fg.setupGUI();
        fg.drawArenaBug();
        UI.printMessage("Move mouse to arena and press Space to start");
        UI.repaintGraphics();
    }   
    
      
 }
     
