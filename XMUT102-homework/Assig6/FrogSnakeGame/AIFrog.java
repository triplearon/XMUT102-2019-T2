
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
    //定义苍蝇位置
    
    private double dX;
    private double dY;
    //定义苍蝇与自身位置差
    
    
    public double AIspeed;
    
    public AIFrog(double x,double h,String dir,String shade){
        super(x,h,dir,shade); //调用父类构造
    }
    
    
    public void turnRight(){}
    
    public void turnLeft(){}
    
    public void turnUp(){}
    
    public void turnDown(){}
    
    //以上 将控制4个方向的方法全都设置为空 覆盖掉父类方法 防止玩家控制AIfrog
    
    public void setBugLocation(double x, double y){
        xBug=x;
        yBug=y;
       
        dX=this.x-xBug;
        dY=this.y-yBug;
        //主函数中调用以设置苍蝇位置
    }
    
    public void setSpeed(Frog g){
        AIspeed=1.0+g.flyCount*0.13;
        //随着玩家苍蝇吃的越来越多 AI青蛙会越来越快
    }
    
    public void move(){
        if(xBug==0 && yBug==0){x=x;y=y; return;}
        
        dX=this.x-xBug;
        dY=this.y-yBug;
        
        //if(xBug<x+size/2 && xBug>x-size/2){isX=false;}else{isX=true;}
        
       
        if(dX<-size/2.0){x+=AIspeed; return;}else if(dX>size/2.0){x-=AIspeed; return;}
   
        if(dY<-size/2.0){y+=AIspeed; return;}else if(dY>size/2.0){y-=AIspeed; return;}
   
        
        //for balance,PC have a little faster than human;
        // 先调整x与目标相同 再调整y与目标相同 以达到和玩家竞争的效果
    }
    
    
}
