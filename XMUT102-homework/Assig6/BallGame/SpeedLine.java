import ecs100.*;
import java.awt.*;


public class SpeedLine
{
    public final static double STARTPX=20;
    public final static double STARTPY=410;
    
    public double endPX;
    public double endPY;
    
    public double k;
    //two point x-x1/x2-x1=y-y1/y2-y1 => ((x-x1)(y2-y1)/y2-y1 +y1)=y
    
    public SpeedLine(double x,double y){
    this.endPY=y;
    this.endPX=x;
   
    }
    
    public void draw(){
        UI.setLineWidth(1);
        if(endPY==20){
            
            return;
        }
        
        if(endPX==20){
        
            return;
        }
        
        for(double i=STARTPX;i<endPX;i+=6){
            UI.drawLine(i,((i-STARTPX)*(endPY-STARTPY)/(endPX-STARTPX))+STARTPY,i+3,((i+3-STARTPX)*(endPY-STARTPY)/(endPX-STARTPX))+STARTPY);
        }
        
        
        
        
    
    }
    
    
    
}
