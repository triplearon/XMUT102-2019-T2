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
import java.util.ArrayList;

/** FrogSnakeGame: Completion
 *  Game with two frogs that race to get to the bug first in order to eat it, and must avoid snakes.
 *  (uses keys: 
 *        player 1: W/A/S/D for up/left/down/right; controls the "light" frog
 *        player 2: I/J/K/L for up/left/down/right; controls the "dark" frog
 *  Frogs move around at a constant speed in an arena with an enclosing wall.
 *  When one of the frog reaches the bug, the frog eats it (grows in size),
 *       and a new bug appears at a random position within the arena walls.
 *
 *  The game has two snakes that constantly move in a random direction until they hits a wall.
 *    In which case the snake changes direction.
 * When a frog touches a snakes, it shrinks back to its original size.
 *
 *  Controls:
 *  - key to start (space)
 *  - keys to turn the two frogs  (w/a/s/d  and i/j/k/l)
 *
 *  Display:
 *   program constantly shows
 *   - the arena, the bug, the frogs and the snakes
 *
 *  Constants:
 *    This class should contain constants specifying the various parameters of
 *    the game, including the geometry of the arena and obstacle.
 */

public class FrogSnakeGame{

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

    // Fields to store the two snakes
    private Snake snake1;
    private Snake snake2;

    //ScoreBoard
    private ScoreBoard sb=new ScoreBoard();
    // Fields to position of the centre of the bug
    private double xBug;
    private double yBug;
    
    public static int totalFlyCount ;
    public int Score1;
    public int Score2;
   

    /** 
     * Set up the GUI
     */
    public void setupGUI(){
        UI.setImmediateRepaint(false);
        UI.setKeyListener(this::doKey);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0);
    }

    /**
     * Respond to keys.
     * the space key should reset the game to have two new frogs
     * the w/a/s/d keys should make the light frog turn up, left, down or right
     * the i/j/k/l keys should make the dark frog turn up, left, down or right
     */
    public void doKey(String key){
        if (key.equals("Space")|| key.equals(" ")|| key.equals("p")) {
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
     * Reset the game with two new frogs in the starting positions,
     *   and two snakes at a random position.
     * Loop forever
     *  - move each frog one step,
     *  - move each snake one step,
     *  - if any frog has caught the bug, it grows, and a new bug appears somewhere else.
     *  - if any frog was caught by a snake, it shrinks back to its original position.
     *  - redraw the game (frogs, snakes, arena, and bug)
     */
    private void restartGame(){
        // make sure that the current game ends (if it is running)
        this.frog1 = null;
        this.frog2 = null;
        this.snake1 = null;
        this.snake2 = null;
        UI.sleep(3*DELAY);

        // puts a bug at a random position
        this.newBug();

        // make new frogs
        this.frog1 = new Frog(LEFT_WALL+30, (TOP_WALL+BOT_WALL)/2, "Up", "light");
        this.frog2 = new Frog(RIGHT_WALL-30, (TOP_WALL+BOT_WALL)/2, "Left", "dark");

        // add 2 snakes
        this.snake1 = new Snake ();
        this.snake2 = new Snake ();

        // call redraw
        this.redraw();
        UI.printMessage("Control the direction of the frogs with keys: a s w d for the lighter frog and j k i l for the darker frog");

   
        snake1.target=frog1;
        snake2.target=frog2;
        
        // Run the game
        while (this.frog1!=null && this.frog2!=null) {
            this.frog1.move();
            this.frog2.move();
            this.snake1.move();
            this.snake2.move();
            if (this.frog1.touching(this.xBug, this.yBug)) {
                totalFlyCount++;
                this.frog1.grow();
                this.newBug();
                Score1=frog1.flyCount;
            }
            else if (this.frog2.touching(this.xBug, this.yBug)) {
                totalFlyCount++;
                this.frog2.grow();
                this.newBug();
                Score2=frog2.flyCount;
            }
            if (this.frog1.touching(this.snake1.getX(), this.snake1.getY()) ||
            this.frog1.touching(this.snake2.getX(), this.snake2.getY()) ) {
                this.frog1 = new Frog(LEFT_WALL+30, (TOP_WALL+BOT_WALL)/2, "Up", "light");
                this.frog1.shrink();
                Score1=0;
                this.redraw();
            }
            if (this.frog2.touching(this.snake1.getX(), this.snake1.getY()) ||
            this.frog2.touching(this.snake2.getX(), this.snake2.getY()) ) {
                this.frog2 = new Frog(RIGHT_WALL-30, (TOP_WALL+BOT_WALL)/2, "Left", "dark");
                this.frog2.shrink();
                Score2=0;
                this.redraw();
                
            }
            
            snake1.chaseForg();
            snake2.chaseForg();
            snake1.target=frog1;
            snake2.target=frog2;
            snake1.setSpeed(totalFlyCount);
            this.redraw();
            UI.sleep(DELAY);
           
        }

    }
    
   


    /**
     * Redraws
     * - the arena
     * - the bug
     * - the two frogs
     * - the two snakes
     */
    private void redraw(){
        UI.clearGraphics();
        this.drawArenaBug();
        this.frog1.draw();
        this.frog2.draw();
        this.snake1.draw();
        this.snake2.draw();
        sb.drawScoreBoard(Score1,Score2);
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

    /**
     * Create a new FrogSnakeGame object, setup the GUI, and draw the arena and the bug
     * 
     */
    public static void main(String[] arguments){
        FrogSnakeGame fg= new FrogSnakeGame();
        fg.setupGUI();
        fg.drawArenaBug();
        UI.printMessage("Move mouse to arena and press Space to start");
        UI.repaintGraphics();
    }   

}
