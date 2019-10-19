 //国旗没画 拒绝体力活！ 

 public class homework{
   public void drawSeychellesFlag(double left, double top, double width){
        UI.clearGraphics();   
        double height = width/2.0;
        //basic                                                           
        UI.setColor(Color.white);
        UI.fillRect(left,top,width,height);
        UI.setColor(Color.black);
        UI.drawRect(left,top,width,height);
        //set background
        UI.setColor(Color.blue);
        double[] Bluex={left,left+1.0/3.0*width,left};
        double[] Bluey={top,top,top+height};
        UI.fillPolygon(Bluex,Bluey,3);
        //blue area
        UI.setColor(Color.yellow);
        double[] Yelx={left+width/3.0,left+2.0/3.0*width,left};
        double[] Yely={top,top,top+height};
        UI.fillPolygon(Yelx,Yely,3);
        //yellow area
        UI.setColor(Color.red);
        double[] Redx={left+2.0/3.0*width,left+width,left+width,left};
        double[] Redy={top,top,top+height/3.0,top+height};
        UI.fillPolygon(Redx,Redy,4);
        //red area
        UI.setColor(Color.green);
        double[] Grex={left+width,left+width,left};
        double[] Grey={top+2.0/3.0*height,top+height,top+height};
        UI.fillPolygon(Grex,Grey,3);
        //green area
        }
        }
