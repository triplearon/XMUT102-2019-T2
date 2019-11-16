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

/** Program for a simple game in which the player has to blow up balloons
 *   on the screen.
 *  The game starts with a collection of randomly placed small balloons
 *    (coloured circles) on the graphics pane.
 *  The player then clicks on balloons to blow them up by a small amount
 *   (randomly increases the radius between 2 and 6 pixels).
 *  If an expanded balloon touches another balloon, then they both "burst" and go grey.
 *  The goal is to get the largest score. The score is the total of the
 *   sizes (areas) of all the active balloons, minus the total size of all
 *   the burst balloons.
 *  At each step, the current score is recalculated and displayed,
 *   along with the highest score that the player has achieved so far.
 *  At any time, the player may choose to stop and "lock in" their score.
 *
 *  The BalloonGame class has a field containing an Arraylist of Balloon objects
 *   to represent the current set of Balloons on the screen.
 *  It has a field to hold the highest score.
 *
 *  The New Game button should start a new game.
 *  The Lock Score button should finish the current game, updating the highest score
 *
 *  Clicking (ie, releasing) the mouse on the graphics pane is the main "action"
 *  of the game. The action should do the following
 *    Find out if the mouse was clicked on top of any balloon.
 *    If so,
 *      Make the balloon a bit larger and redraw it.
 *      Check whether the balloon is touching any other balloon.
 *      If so
 *         burst the two balloons (which will make them go grey)
 *         redraw the burst Balloons
 *      Recalculate and redisplay the score
 *   If all the balloons are gone, the game is over.
 *    
 *   To start a game, the program should
 *       Clear the graphics pane
 *       Initialise the score information
 *       Make a new list of Balloons at random positions
 *       Print a message 
 *
 *   If the game is over, the program should
 *      Update the highest score if the current score is better,
 *      Print a message reporting the scores,
 *      Start a new game.
 *     
 *   There are lots of ways of designing the program. It is not a good idea
 *   to try to put everything into one big method.
 *        
 *  Note that the Balloon class is written for you. Make sure that you know
 *   all its methods - no marks for redoing code that is given to you.
 *    
 */
public class BalloonGame {
    private static final int MAX_BALLOONS = 20;

    private ArrayList <Balloon> balloons = new ArrayList<Balloon>(); // The list of balloons
                                                                     // (initially empty)

    // Fields
    private int score=0;
    private int highScore=0;
    private boolean isGameOver=false;
    private boolean isScoreLock=false;
    private boolean pushMode=false;
    private int balloonsAmount=0;
    private int pushTimes;
    private int ApushTimes;
    
    
    
    private ArrayList<Double> pX=new ArrayList<>();
    private ArrayList<Double> pY=new ArrayList<>();
    private Balloon ball;
    
    /** Set up the GUI */
    public void setupGUI(){
        UI.setWindowSize(600,600);
        UI.setMouseListener(this::doMouse);
        UI.setKeyListener(this::doKey);
        UI.addSlider("Amount",2.0,20.0,this::setAmount);
        UI.addButton("New Game",this::restartGame);
        UI.addButton("Lock Score",this::doSetLock);
        UI.addButton("Push",this::doSetPush);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);
    }
    
    public void setAmount(double amount){
        int num=(int) amount;
        if(num % 2==0){
            balloonsAmount=num;
        }else{
            balloonsAmount=num+1;
        }
        
    }
    public void doSetPush(){
        if(pushTimes>0){
        this.pushMode=true;
        this.ApushTimes=5;
        pushTimes--;
      }
      if(pushTimes==1){UI.printMessage("There is only one chance you push a ball"); return;}
      
      if(pushTimes==0){UI.printMessage("You have no chance to do this!"); return;}
      UI.printMessage("You left "+pushTimes+" times to push a ball");
    }
    public void doKey(String key){
        if(this.ball==null || !pushMode){return;}
        
        if(pushMode && !(this.ball==null)){
            
            if(!(key==null)){
                ball.clear();
                switch(key){
                    case "w":
                     ball.centerY-=10;
                     ApushTimes--;
                     break;
                    
                    case "s":
                     ball.centerY+=10;
                     ApushTimes--;
                     break;
                     
                    case "a":
                     ball.centerX-=10;
                     ApushTimes--;
                     break;
                     
                    case "d":
                     ball.centerX+=10;
                     ApushTimes--;
                     break;
                }
            }
           this.findTouching(ball);
           ball.draw();
         if(ApushTimes==0){pushMode=false;}
      }
    }

    /** Start the game:
     *  Clear the graphics pane
     *  Initialise the score information 
     *  Make a new set of Balloons at random positions
     */
    public void restartGame(){
        UI.clearGraphics();
        pX.clear();
        pY.clear();
        balloons.clear();
        score=0;
        highScore=0;
        isScoreLock=false;
        pushTimes=3;
        
        
        
        
        this.setBalloon();
        for(int i=0;i<pX.size();i++){
            balloons.add(new Balloon(pX.get(i),pY.get(i)));
        }
        
        
        
        while(true){
                for(Balloon ball:balloons){
                ball.draw();
            }
            
            if(allBalloonsBurst()){
                this.endGame();
                break;
            }
            UI.sleep(100);
        }
    }
    
    public void setBalloon(){
        int amount=0;
        while(amount<balloonsAmount){
         double xPosition=10+Math.random()*580;
         double yPosition=10+Math.random()*580;
         if(caculateDis(xPosition,yPosition)){
            pX.add(xPosition);
            pY.add(yPosition);
            amount++;
            }
        }
    }
    
    private boolean caculateDis(double x, double y){
        for(int i=0;i<pX.size();i++){
            if(this.getDist(x,y,pX.get(i),pY.get(i))<Balloon.INITALRADIUS*2){
                return false;
            }
        }
        return true;
    }
    
    private double getDist(double x1,double y1,double x2,double y2){
        return Math.hypot(x1-x2,y1-y2);
    }

    /**
     * Main game action:
     *  Find the balloon at (x,y) if any,
     *  Expand it 
     *  Check whether it is touching another balloon,
     *  If so, update the penalty, burst both balloons.
     *  Report the score.
     *  If there are no active balloons left, end the game.
     */
    public void  doMouse(String action, double x, double y){
        if(action.equals("pressed")){
            ball=this.findBalloonOn(x,y);
            
            if(ball==null){ return;}
            
            ball.expand();
            this.findTouching(ball);
            this.randomBurst();
            this.calculateScore();
            UI.printMessage("Now Score: "+this.score+" High Score: "+this.highScore);
           
        }

    }

    
    public Balloon findBalloonOn(double x, double y){
            
            
            for(Balloon ball:balloons){
                if(ball.on(x,y)){
                    return ball;
                }
            }
            
            return null;
    }
    //       for finding the (active) balloon that the point (x,y) is on, if any

    public void findTouching(Balloon balloon){
        int index=balloons.indexOf(balloon);
        for(int i=0;i<balloons.size();i++){
            if(i==index){continue;}
            
            if(balloon.isTouching(balloons.get(i))){
                if(!balloons.get(i).getActive()){continue;}
                balloons.get(i).burst();
                balloon.burst();
                return;
            }
        }
        
        
    }
    //       for finding another active balloon touching the given one.

    public void calculateScore(){
        int positive=0;
        int negative=0;
        if(!isScoreLock){
        for(Balloon ball:balloons){
            if(ball.getActive()){
                positive+=ball.size();
            }else{
                negative+=ball.size();
            }
        }
        
        this.score=positive-negative;
        if(this.score>this.highScore){
            this.highScore=this.score;
        }
      }
      
    }
    //       for calculating the current score
    public void doSetLock(){
        this.isScoreLock=true;
    }
    
    public boolean allBalloonsBurst(){
        for(Balloon ball:balloons){
            if(ball.getActive()){
                return false;
            }
        }
        
        return true;
    }
    //       to find out if all the balloons have been burst.
    
    public void randomBurst(){
        int count=0;
        for(Balloon ba:balloons){
            if(ba.getActive()){
                count++;
            }
        }
        
        if(count <4){return;}
        
        double randomControl=Math.random();
        double e=1E-7;
        if(randomControl-0.0314159<e){
            int ballIndex=balloons.indexOf(this.ball);
            double a1=1E10,a2=1E10,a3=1E10; //a1 is the shortest dis;
            int a1Index=-1,a2Index=-1,a3Index=-1;
            
            for(int i=0;i<balloons.size();i++){
                if(i==ballIndex){continue;}
                if(!balloons.get(i).getActive()){continue;}
                double a=balloons.get(ballIndex).getDist(balloons.get(i));
                
                if(a<a2){
                    if(a<a1){
                        a3=a2;
                        a3Index=a2Index;
                        a2=a1;
                        a2Index=a1Index;
                        a1=a;
                        a1Index=i;
                    }else{
                        a3=a2;
                        a3Index=a2Index;
                        a2=a;
                        a2Index=i;
                    }
                }else if(a<a3){
                    a3=a;
                    a3Index=i;
                }
                //find and sort
                
            }
            this.ball.burst();
            balloons.get(a1Index).burst();
            balloons.get(a2Index).burst();
            balloons.get(a3Index).burst();
        }
    }
    public void endGame(){
        UI.setDivider(0.3);
        UI.println("Ball numbers: "+this.balloonsAmount);
        UI.println("Now your score is " +this.score);
        UI.println("Your higest score was " +this.highScore);
    }
    //        to update the highestScore and print a message

   
    /**
     * Main
     */
    public static void main(String[] arguments){
        new BalloonGame().setupGUI();

    }   

}
