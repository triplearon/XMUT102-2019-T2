// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 7
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class MiniPaint{
    
    protected String shape="Line";
    protected boolean isFill=false;

    
    protected double xPressed;
    protected double yPressed;
    protected String imageName;
    protected Color colour;
    protected boolean isComplex=false;
    
    /** Set up the GUI */
    public void setupGUI(){
        UI.setMouseListener( this::doMouse );
        UI.addButton("Clear", this::clearGraphics);
        UI.addButton("Fill/No Fill",this::doSetFill);
        UI.addButton("Color",this::doSetColour);
        UI.addButton("Line",this::doSetLine);
        UI.addButton("Rect",this::doSetRect);
        UI.addButton("Oval",this::doSetOval);
        UI.addButton("Image",this::doSetImage);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0);  // Hide the text area.
    }
    
    // Methods to change the shape that will be drawn when the mouse is next released.
    // These methods just save information to the fields.
    /** Respond to the Line button */
    public void clearGraphics(){
        UI.clearGraphics();
        showCondition();
    }
    
    public void doSetLine(){
        this.shape="Line";
        this.isComplex=false;
        this.showCondition();
    }
    
 

    /** Respond to the Rect button */
    public void doSetRect(){
        this.shape="Rect";
        this.isComplex=false;
        this.showCondition();
    }

    /** Respond to the Oval button */
    public void doSetOval(){
        this.shape="Oval";
        this.isComplex=false;
        this.showCondition();
    }

    /** Respond to the Image button */
    public void doSetImage(){
        this.shape="Image";
        imageName=UIFileChooser.open();
        this.isComplex=false;
        this.showCondition();
    }

    /** Respond to the Colour button */
    public void doSetColour(){
        colour=JColorChooser.showDialog(null,"Choose one color",Color.black);
        showCondition();
        
    }

    /** Respond to the Fill/Nofill button */
    public void doSetFill(){
        if(isFill){
        isFill=false;
       
        }else{
        isFill=true;
    
        }
        showCondition();
    }

    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw what is specified by current shape
     * Uses the value stored in the field to determine which kind of shape to draw.
     *  It should call the drawALine, drawARectangle, drawAnOval, or drawAnImage, methods
     *  passing the x and y where the mouse was released.
     */
    public void doMouse(String action, double x, double y) {
        if(action.equals("pressed")){
            xPressed=x;
            yPressed=y;
        }else if(action.equals("released")){
            switch(shape){
                case "Line":this.drawALine(x,y); break;
                case "Rect":this.drawARectangle(x,y);break;
                case "Oval":this.drawAnOval(x,y);break;
                case "Image": this.drawAnImage(x,y);break;
                default :UI.println("Please choose a pattern");
          }
      }
    }

    /**
     * Draw a line between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     */
    public void drawALine(double x, double y){
       UI.drawLine(xPressed,yPressed,x,y);
    }

    /**
     * Draw a rectangle between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     * Works out the left, top, width, and height from x, y, lastX and lastY
     * Then draws the rectangle, filled or outline, depending on the fill field.
     */
    public void drawARectangle(double x, double y){
        double width=x-xPressed;
        double height=y-yPressed;
        if(!isFill){
        
        if(width>0 && height>0) {UI.drawRect(xPressed,yPressed,width,height);return;}
        
        if(width>0 && height<0) {UI.drawRect(xPressed,y,width,Math.abs(height));return;}
        
        if(width<0 && height>0) {UI.drawRect(x,yPressed,Math.abs(width),height);return;}
        
        if(width<0 && height<0) {UI.drawRect(x,y,Math.abs(width),Math.abs(height));return;}
     } else{
         
         if(width>0 && height>0) {UI.fillRect(xPressed,yPressed,width,height);return;}
        
        if(width>0 && height<0) {UI.fillRect(xPressed,y,width,Math.abs(height));return;}
        
        if(width<0 && height>0) {UI.fillRect(x,yPressed,Math.abs(width),height);return;}
        
        if(width<0 && height<0) {UI.fillRect(x,y,Math.abs(width),Math.abs(height));return;}
        }
    }
    

    /**
     * Draw an oval between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     * Works out the left, top, width, and height from x, y, lastX and lastY
     * Then draws the oval, filled or outline, depending on the fill field.
     */
    public void drawAnOval(double x, double y){
        double width=x-xPressed;
        double height=y-yPressed;
        if(!isFill){
        
        if(width>0 && height<0){
            UI.drawOval(xPressed,y,width*2,Math.abs(height)*2);
        }else
        
        if(width>0 && height>0){
            UI.drawOval(xPressed,y-2*height,width*2,height*2);
        }else
        
        if(width<0 && height>0){
            UI.drawOval(xPressed-2*Math.abs(width),y-2*height,Math.abs(width)*2,height*2);
        }else
        
        if(width<0 && height<0){
            UI.drawOval(xPressed-2*Math.abs(width),y,Math.abs(width)*2,Math.abs(height)*2);
        }
      }else{
        if(width>0 && height<0){
            UI.fillOval(xPressed,y,width*2,Math.abs(height)*2);return;
        }else
        
        if(width>0 && height>0){
            UI.fillOval(xPressed,y-2*height,width*2,height*2);return;
        }else
        
        if(width<0 && height>0){
            UI.fillOval(xPressed-2*Math.abs(width),y-2*height,Math.abs(width)*2,height*2);return;
        }else
        
        if(width<0 && height<0){
            UI.fillOval(xPressed-2*Math.abs(width),y,Math.abs(width)*2,Math.abs(height)*2);return;
        }
        }
    }

    /** 
     * Draws the current image between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     * Works out the left, top, width, and height from x, y, lastX and lastY
     * Then draws the image, if there is one.
     */
    public void drawAnImage(double x, double y){
        double width=x-xPressed;
        double height=y-yPressed;
     
        
        if(width>0 && height>0) {UI.drawImage(imageName,xPressed,yPressed,width,height);} else
        
        if(width>0 && height<0) {UI.drawImage(imageName,xPressed,y,width,Math.abs(height));} else
        
        if(width<0 && height>0) {UI.drawImage(imageName,x,yPressed,Math.abs(width),height);} else
        
        if(width<0 && height<0) {UI.drawImage(imageName,x,y,Math.abs(width),Math.abs(height));} 
     
    }
    
    protected void showCondition(){
        UI.eraseRect(0,0,20,20);
        UI.setColor(colour);
        
        if(shape.equals("Rect")){
            if(!isFill){
            UI.drawRect(0,0,20,20); 
        }else{
            UI.fillRect(0,0,20,20);
        }
      }else
      
        if(shape.equals("Oval")){
            if(!isFill){
                UI.drawOval(0,0,20,20);
            }else{
                UI.fillOval(0,0,20,20);
            }
        }else
        
        if(shape.equals("Image")){
            UI.drawImage(imageName,0,0,20,20);
        }else
        
        if(shape.equals("Line")){
            UI.drawLine(0,0,20,20);
        }
}
    
    // Main:  constructs a new MiniPaint object and call the setupGUI method
    public static void main(String[] arguments){
        MiniPaint mp = new MiniPaint();
        mp.showCondition();
        mp.setupGUI();

    }   

}
