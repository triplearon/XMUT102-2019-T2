
/**
 * A frog which controled by CPU
 *
 * name:Deng XinYu
 * number:1812409208
 * ID:xmut_1812409208
 */
public class AIFrog extends Frog
{
    private double xBug;
    private double yBug;
    
    private double dX;
    private double dY;
    
    
    
    public double AIspeed;
    
    public AIFrog(double x,double h,String dir,String shade){
        super(x,h,dir,shade);
    }
    
    public void turnRight(){}
    
    public void turnLeft(){}
    
    public void turnUp(){}
    
    public void turnDown(){}
    
    public void setBugLocation(double x, double y){
        xBug=x;
        yBug=y;
       
        dX=this.x-xBug;
        dY=this.y-yBug;
    }
    
    public void setSpeed(Frog g){
        AIspeed=1.0+g.flyCount*0.13;
    }
    
    public void move(){
        if(xBug==0 && yBug==0){x=x;y=y; return;}
        
        dX=this.x-xBug;
        dY=this.y-yBug;
        
        //if(xBug<x+size/2 && xBug>x-size/2){isX=false;}else{isX=true;}
        
       
        if(dX<-size/2.0){x+=AIspeed; return;}else if(dX>size/2.0){x-=AIspeed; return;}
   
        if(dY<-size/2.0){y+=AIspeed; return;}else if(dY>size/2.0){y-=AIspeed; return;}
   
        
        //for balance,PC have a little faster than human;
    }
    
    
}
