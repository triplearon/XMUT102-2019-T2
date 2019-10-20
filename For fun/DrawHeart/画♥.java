import ecs100.*;
import java.awt.*;

public class Heart
{   double x;
    double y;
    double size;
    double[] x1;
    double[] y1;
    
    public Heart(double x,double y,double size){
        this.x=x;
        this.y=y;
        double [] x2={x,x+size,x,x-size};
        double [] y2={y-size,y,y+size,y};
        this.x1=x2;
        this.y1=y2;
        this.size=size;
    }
    
    public void drawHeart(){
        UI.setColor(new Color(255,0,0));
        UI.fillPolygon(x1,y1,4);
        double diam=Math.sqrt(2*this.size*this.size);
        UI.fillArc((2*x-size-diam)/2,(2*y-size-diam)/2,diam,diam,45,180);
        UI.fillArc((2*x+size-diam)/2,(2*y-size-diam)/2,diam,diam,-45,180);
    }
    
    
    public static void main(String[] args){
        Heart a=new Heart(UI.askDouble("x"),UI.askDouble("y"),UI.askDouble("size"));
        a.drawHeart();
        
    }
    
}
