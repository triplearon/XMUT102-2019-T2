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
import java.util.*;

public class MiniPaintChallenge extends MiniPaint{
    private static final double[] POLYGONX={0,0,20};
    private static final double[] POLYGONY={0,20,20};
    private double lastDX;
    private double lastDY;
    private double lastY;
    private double lastX;
    private double lineX;
    private double lineY;
    private ArrayList<Double> polygonX=new ArrayList<>();
    private ArrayList<Double> polygonY=new ArrayList<>();
    private int points;
    private ArrayList<Double> controlX=new ArrayList<>();
    private ArrayList<Double> controlY=new ArrayList<>();
    
    public void setupGUI(){
        UI.setMouseMotionListener( this::doMouse );
        //role
        UI.addButton("Clear", this::clearGraphics);
        UI.addButton("Quit", UI::quit);
        UI.addButton("Fill/No Fill",this::doSetFill);
        UI.addButton("Color",this::doSetColour);       
        UI.addButton("Eraser",this::doSetEraser);
        //basic
        UI.addButton("Line",this::doSetLine);
        UI.addButton("Rect",this::doSetRect);
        UI.addButton("Oval",this::doSetOval);
        UI.addButton("Image",this::doSetImage);
        //complex
        UI.addButton("Polygon",this::doSetPolygon);
        UI.addSlider("Side",3,10,3,this::setSide);
        UI.addButton("Bezier",this::doSetBezier);
        
        
        UI.setDivider(0);  // Hide the text area.
    }   
    public void doMouse(String action, double x, double y){
        if(!isComplex){
        if(action.equals("moved") && shape.equals("Eraser")){
            UI.eraseOval(x-10,y-10,20,20);
            return;
        }
        if(action.equals("pressed")){
            xPressed=x;
            yPressed=y;
        }else if(action.equals("dragged")){
            switch(shape){
                case"Line" :this.dragALine(x,y); break;
                case"Rect" :this.dragARect(x,y); break;
                case"Oval" :this.dragAnOval(x,y); break;
                case"Image":this.dragARect(x,y); break;
            
            }
            
        }else if(action.equals("released")){    
            switch(shape){
                case"Line" :this.drawALine(x,y); break;
                case"Rect": this.drawARectangle(x,y); break;
                case"Oval": this.drawAnOval(x,y); break;
                case"Image": this.drawAnImage(x,y); break;
            }
            lastDX=0; lastDY=0; lastX=0; lastY=0;
        
        }
       }else{
           if(action.equals("pressed")){
             switch(shape){
               case"Polygon":drawAPolygon(x,y);break;
               case"Bezier":drawABezier(x,y); break;
            }
        
        }
    }
    
    }
    
    private void dragALine(double x, double y){
        if(lastX!=0 && lastY!=0){
        UI.invertLine(xPressed,yPressed,lastX,lastY);
      }
        UI.invertLine(xPressed,yPressed,x,y);
        
        lastX=x;
        lastY=y;
    }
    
    private void dragARect(double x,double y){
        double dx=x-xPressed;
        double dy=y-yPressed;
        if(lastDX>0 && lastDY>0){
            UI.invertRect(xPressed,yPressed,lastDX,lastDY);
        }else if(lastDX>0 && lastDY<0){
            UI.invertRect(xPressed,lastY,lastDX,-lastDY);
        }else if(lastDX<0 && lastDY<0){
            UI.invertRect(lastX,lastY,Math.abs(lastDX),Math.abs(lastDY));
        }else if(lastDX<0 && lastDY>0){
            UI.invertRect(lastX,yPressed,Math.abs(lastDX),lastDY);
        }
        
        if(dx>0 && dy>=0){
            UI.invertRect(xPressed,yPressed,dx,dy);
        }else if(dx>0 && dy<=0){
            UI.invertRect(xPressed,y,dx,Math.abs(dy));
        }else if(dx<0 && dy<0){
            UI.invertRect(x,y,Math.abs(dx),Math.abs(dy));
        }else if(dx<0 && dy>0){
            UI.invertRect(x,yPressed,Math.abs(dx),dy);
        }
        
        lastDX=dx;
        lastDY=dy;
        lastY=y;
        lastX=x;
    }
    
    private void dragAnOval(double x, double y){
        double dx=x-xPressed;
        double dy=y-yPressed;
        //clear last times oval
        if(lastDX>0 && lastDY>0){
            UI.invertOval(xPressed,lastY-2*lastDY,lastDX*2,lastDY*2);
        }else if(lastDX>0 && lastDY<0){
            UI.invertOval(xPressed,lastY,lastDX*2,Math.abs(lastDY)*2);
        }else if(lastDX<0 && lastDY<0){
            UI.invertOval(xPressed-Math.abs(lastDX)*2,lastY,Math.abs(lastDX)*2,Math.abs(lastDY)*2);
        }else if(lastDX<0 && lastDY>0){
            UI.invertOval(lastX-Math.abs(lastDX),yPressed-lastDY,Math.abs(lastDX)*2,lastDY*2);
        }
        
        if(dx>0 && dy>0){
            UI.invertOval(xPressed,y-2*dy,dx*2,dy*2);
        }else if(dx>0 && dy<0){
            UI.invertOval(xPressed,y,dx*2,Math.abs(dy)*2);
        }else if(dx<0 && dy<0){
            UI.invertOval(xPressed-Math.abs(dx)*2,y,Math.abs(dx)*2,Math.abs(dy)*2);
        }else if(dx<0 && dy>0){
            UI.invertOval(x-Math.abs(dx),yPressed-dy,Math.abs(dx)*2,dy*2);
        }
        
        lastDX=dx;
        lastDY=dy;
        lastY=y;
        lastX=x;
        
    }
    
    private void drawABezier(double x, double y){
        if(lastX!=0 && lastY!=0){
            UI.invertLine(lastX,lastY,x,y);
        }
        if(controlX.size()<4){
            controlX.add(x);
            controlY.add(y);
           
        }
        
        if(controlX.size() == 4){
            UI.invertLine(controlX.get(0),controlY.get(0),controlX.get(1),controlY.get(1));
            UI.invertLine(controlX.get(1),controlY.get(1),controlX.get(2),controlY.get(2));
            UI.invertLine(controlX.get(2),controlY.get(2),controlX.get(3),controlY.get(3));
            
            int t=1;
            for(double i=0;i<t;i+=0.0001){
                double pX=controlX.get(0)*(1-i)*(1-i)*(1-i)+3*controlX.get(1)*i*(1-i)*(1-i)+3*controlX.get(2)*i*i*(1-i)+controlX.get(3)*i*i*i;
                double pY=controlY.get(0)*(1-i)*(1-i)*(1-i)+3*controlY.get(1)*i*(1-i)*(1-i)+3*controlY.get(2)*i*i*(1-i)+controlY.get(3)*i*i*i;
                
                UI.drawLine(pX,pY,pX,pY);
            }
            
            
            controlX.clear();
            controlY.clear();
            lastX=0;
            lastY=0;
            return;
        }
        
        lastX=x;
        lastY=y;
    }
    
    private void drawAPolygon(double x, double y){
        
        UI.drawLine(x,y,x,y);
        
        if(polygonX.size()<points){
            polygonX.add(x);
            polygonY.add(y);
            if(polygonX.size()<points){
            return;
        }
        }
        
        double[] xPoints=MiniPaintChallenge.arraylistToArray(polygonX);
        double[] yPoints=MiniPaintChallenge.arraylistToArray(polygonY);
        
        if(isFill){UI.fillPolygon(xPoints,yPoints,points);} else UI.drawPolygon(xPoints,yPoints,points);
        polygonX.clear();
        polygonY.clear();
        xPoints=null;
        yPoints=null;
    }
    
    private static double[] arraylistToArray(ArrayList<Double> a){
        double[] ans=new double[a.size()];
        for(int i=0;i<a.size();i++){
            ans[i]=a.get(i);
        }
        
        return ans;
    }
    
    protected void showCondition(){
        super.showCondition();
        if(shape.equals("Polygon")){
            if(!isFill){
                UI.drawPolygon(POLYGONX,POLYGONY,3);
            }else{
                UI.fillPolygon(POLYGONX,POLYGONY,3);
            }
        }else
        
        if(shape.equals("Bezier")){
            UI.drawLine(0,0,20,20);
            UI.drawLine(20,20,20,0);
            UI.drawLine(20,0,0,20);
        }
    }
    
    
    private void doSetEraser(){
        this.shape="Eraser";
        this.isComplex=false;
        showCondition();
    }
    
    private void doSetPolygon(){
        this.shape="Polygon";
        this.isComplex=true;
        showCondition();
    }
    
    private void doSetBezier(){
        this.shape="Bezier";
        this.isComplex=true;
        showCondition();
    }
    
    private void setSide(double x){
        points=(int)x;
    }
    // Main:  constructs a new MiniPaintChallenge object
    public static void main(String[] args){
        MiniPaintChallenge mpc = new MiniPaintChallenge();
        mpc.showCondition();
        mpc.setupGUI();
    }   

}
