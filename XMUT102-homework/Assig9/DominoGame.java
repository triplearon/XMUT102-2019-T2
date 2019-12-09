// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 9
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;


/**
 *  Lets a player play a simple Solitaire dominoes game.
 *  Dominoes are rectangular tiles with two numbers from 0 to 6 on
 *  them (shown with dots).
 *  The player has a "hand" which can contain up to six dominoes.
 *  They can reorder the dominoes in their hand, they can place dominoes
 *  from their hand onto the table, and they can pick up more dominoes from
 *  a bag to fill the gaps in their "hand".
 *  The core and completion do not involve any of the matching and scoring
 *  of real dominoes games. 
 *
 *  PROGRAM DESIGN
 *  The dominoes are represented by objects of the Domino class.
 *  The Domino constructor will construct a new, random domino.
 *  Dominos have a draw(double x, double y) method that will draw the
 *  Domino on the graphics pane at the specified position.
 *  
 *  The program has two key fields:
 *    hand:  an array that can hold 6 Dominos. 
 *    table: an ArrayList of the Dominos that have been placed on the table.
 *    
 *  The hand should be displayed near the top of the Graphics pane with a
 *   rectangular border and each domino drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected domino (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected domino
 *  (or the space) to the left or the right, in which case the domino is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a domino, the user
 *  can use the "Place" button to move the selected domino to the table.
 *  
 *  If there are any empty positions on the hand, the user can use the
 *  "Pickup" button to get a new (random) domino which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of dominos.
 *  At the beginning of the game the table should be empty.
 *  Dominos should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class DominoGame{
    public static final int NUM_HAND = 6;    // Number of dominos in hand

    // Fields: hand, table and selectedPos
    private Domino[] hand=new Domino[6];
    private ArrayList<Domino> table=new ArrayList<>();

    private int selectedPos = 0;      //  selected position in the hand.
    
    boolean isMouseMove=false;

    // (You shouldn't add any more fields for core or completion)

    // constants for the layout
    public static final int HAND_LEFT = 60; // x-position of the leftmost Domino in the hand
    public static final int HAND_TOP = 5;   // y-Position of all the Dominos in the hand 
    public static final int DOMINO_SPACING = 104; 
    //spacing is the distance from left side of Domino to left side of next domino
    public static final int DOMINO_HEIGHT = 50; 

    public static final int TABLE_LEFT = 10;                
    public static final int TABLE_TOP = 120;   

    /**
     * Restart the game:
     *  set the table to be empty,
     *  set the hand to have no dominos
     */
    public void restart(){
        this.hand=new Domino[6];
        this.table=new ArrayList<>();
        this.redraw();
   }

    /**
     * If there is at least one empty position on the hand, then
     * create a new random domino and put it into the first empty
     * position on the hand.
     * (needs to search along the array for an empty position.)
     */
    public void pickup(){
        for(int i=0;i<this.hand.length;i++){
            if(this.hand[i] == null){
                hand[i]=new Domino();
                break;
            }
        }

        this.redraw();
   }

    /**
     * Draws the outline of the hand,
     * draws all the Dominos in the hand,
     * highlights the selected position in some way
     * This needs to use the constants:
     *   DOMINO_SPACING, DOMINO_HEIGHT, HAND_LEFT, HAND_TOP
     */
    public void drawHand(){
        for(int i=0;i<this.hand.length;i++){
            if(!(this.hand[i]==null)){
                this.hand[i].draw(HAND_LEFT+i*DOMINO_SPACING,HAND_TOP);
            }
        }
        
        UI.setColor(Color.green);
        UI.setLineWidth(3);
        UI.drawRect(HAND_LEFT+selectedPos*DOMINO_SPACING,HAND_TOP,DOMINO_SPACING-4,DOMINO_HEIGHT);
   }

    /**
     * Move domino from selected position on hand (if there is domino there) to the table
     * The selectedPos field contains the index of the selected domino.
     */
    public void placeDomino(){
        if(this.hand[selectedPos]==null){return;}
        
        
        this.table.add(this.hand[selectedPos]);
        this.hand[selectedPos]=null;
        this.redraw();
        
   }

    /**
     * Draws the list of Dominos on the table, 7 to a row
     * Note, has to wrap around to a new row when it gets to the
     * edge of the table
     * This needs to use the constants:
     *   DOMINO_SPACING, DOMINO_HEIGHT, TABLE_LEFT, TABLE_TOP
     */
    public void drawTable(){
        for(int i=0,c=0,r=0;i<this.table.size();i++){
            this.table.get(i).draw(TABLE_LEFT+c*DOMINO_SPACING,TABLE_TOP+r*(5+DOMINO_HEIGHT));
            
            if(c<6){
            c++;
            
            }else{
            r++;
            c=0;
        }
    }
   }

    /**
     * If there is a domino at the selected position in the hand, 
     * flip it over.
     */
    public void flipDomino(){
        if(this.hand[selectedPos]==null){return;}
        
        this.hand[selectedPos].flipNums();
        this.redraw();
   }

    /**
     * Swap the contents of the selected position on hand with the
     * position on its left (if there is such a position)
     * and also decrement the selected position to follow the domino 
     */
    public void moveLeft(){
        if(selectedPos == 0) {return;}
        
        Domino mid=this.hand[selectedPos-1];
        this.hand[selectedPos-1]=this.hand[selectedPos];
        this.hand[selectedPos]=mid;
        this.redraw();
   }

    /**
     * Swap the contents of the selected position on hand with the
     *  position on its right (if there is such a position)
     *  and also increment the selected position to follow the domino 
     */
    public void moveRight(){
         if(selectedPos == 5) {return;}
        
        Domino mid=this.hand[selectedPos+1];
        this.hand[selectedPos+1]=this.hand[selectedPos];
        this.hand[selectedPos]=mid;

        this.redraw();
   }

    /**
     * If the table is empty, only a double (left and right the same) can be suggested.
     * If the table is not empty, see if one domino has a number that matches the right 
     *    number of the last domino on the table.
     */
    public void suggestDomino(){
        boolean haveSuggestion=false;
        if(this.table.size()==0){
            for(Domino handToTable:this.hand){
                if(handToTable == null){break;}
                if(handToTable.getLeftNum()==handToTable.getRightNum()){
                    UI.println("Suggesting domino ["+handToTable.getLeftNum()+" | "+handToTable.getRightNum()+"]");
                    haveSuggestion=true;
                }
            }
        }else{
            for(Domino handToTable:this.hand){
                if(handToTable == null){continue;}
                if(
                   handToTable.getLeftNum()==this.table.get(this.table.size()-1).getRightNum() ||
                   handToTable.getRightNum()==this.table.get(this.table.size()-1).getRightNum()
                ){
                  UI.println("Suggesting domino ["+handToTable.getLeftNum()+" | "+handToTable.getRightNum()+"]");
                  haveSuggestion=true;
                }
            }
            
        }
        
        if(!haveSuggestion){
            UI.println("No suggestion");
        }

   }

    /** Allows the user to select a position in the hand using the mouse.
     * If the mouse is released over the hand, then sets  selectedPos
     * to be the index into the hand array.
     * Redraws the hand and table */
    public void doMouse(String action, double x, double y){
        if(!isMouseMove){
        if (action.equals("released")){
            if (y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/DOMINO_SPACING);
                UI.clearText();UI.println("selected "+(this.selectedPos+1));
                this.redraw();
            }
        }
        }
        else{
        if(action.equals("pressed")){
            if (y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/DOMINO_SPACING);
                UI.clearText();UI.println("selected "+(this.selectedPos+1));
                this.redraw();
            }
        }else if(action.equals("released") && this.hand[selectedPos] != null){
            boolean isInsert=false;
            int rows=0;
            int cols=0;
                
            for(int i=0;i<this.table.size();i++){
           if(x>=TABLE_LEFT+cols*DOMINO_SPACING && x<=TABLE_LEFT+(cols+1)*DOMINO_SPACING && y>=TABLE_TOP+rows*(5+DOMINO_HEIGHT) && y<TABLE_TOP+rows*(5+DOMINO_HEIGHT)+DOMINO_HEIGHT){
               int index=rows*6+cols;
               this.table.add(index,this.hand[selectedPos]);
               this.hand[selectedPos]=null;
               isInsert=true;
               break;
           }   
            
           if(cols<6){
            cols++;
            
           }
            else{
                rows++;
                cols=0;
           }
        }
            
            if(!isInsert && !(y >= HAND_TOP && y <= HAND_TOP+DOMINO_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*DOMINO_SPACING)){
                this.table.add(this.hand[selectedPos]);
                this.hand[selectedPos]=null;
            }
            
            this.redraw();
        }
       }
   }
    
        /**
     *  Redraw the table and the hand.
     */
    public void redraw(){
        UI.clearGraphics();
        
        this.drawHand();
        this.drawTable();
        
        UI.setLineWidth(1);
        UI.setColor(Color.black);
        UI.drawRect(50,0,640,60);
   }
    
    public void setMouseMove(){
        this.isMouseMove=!isMouseMove;
        
        if(isMouseMove){
        UI.println("MouseMove On");
        }else{
        UI.println("MouseMove Off");
        }
   }

    public void setupGUI(){
        UI.addButton("Pickup",this::pickup);
        UI.addButton("Place",this::placeDomino);
        UI.addButton("Flip",this::flipDomino);
        UI.addButton("Left",this::moveLeft);
        UI.addButton("Right",this::moveRight);
        UI.addButton("Suggestion",this::suggestDomino);
        UI.addButton("MouseMove(beta)",this::setMouseMove);
        UI.addButton("Start",this::restart);
        UI.addButton("Quit", UI::quit);
        
        UI.setMouseListener(this::doMouse);
        UI.setWindowSize(1100,500);
   }

    public static void main(String[] args){
        new DominoGame().setupGUI();

   }   
}
