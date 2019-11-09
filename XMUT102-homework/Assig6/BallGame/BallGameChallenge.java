
import ecs100.*;
import java.awt.Color;
import java.util.*;

//XMUT_1812409208
//Deng XinYu

public class BallGameChallenge{

      public static final double GROUND = 450;
    public static final double LAUNCHER_X = 20;      // The initial position of the ball being launched
    public static final double LAUNCHER_HEIGHT = 430; // The initial height of the ball being launched
    public static final double RIGHT_END = 600;
    public static final double SHELF_X12 = 380;
    public static final double SHELF_X34 = 360;
    public static final double SHELF_X56 = 400;
    public static final double MAX_SPEED = 15;
    public static final double RADIUS=C_Ball.RADIUS;
    public static int count;
    public static int countmove;
    
    private C_Ball ball; // the ball that is being launched towards the target
    // need to be in a field because two different methods need to access it.
    
    private ArrayList<C_Ball> targetList=new ArrayList<>();
    private HashSet<Double> targetH=new HashSet<Double>();
    private ArrayList<Obstacles> obstaclesList =new ArrayList<>();
    private boolean isLaunch=false;
    private SpeedLine line;
    private boolean gameIsStart=false;
    private boolean speedLineVisible=false;
    static{
        UI.println("======================================================================================");
        UI.println("Move the mouse slowly or cancel speed line please");
        UI.println("Because the null pointer.");
        UI.println("If you want to reset the ball immediately");
        UI.println("Or, the ball stop on a right X but not the Y");
        UI.println("Please press the left button\"reset\" many thanks");
        UI.println("======================================================================================");
        UI.println("");
    }
    
    
    public void launch(String action, double x, double y){
        if(!isLaunch && gameIsStart){
            
            if(action.equals("moved") && speedLineVisible){
                countmove++;
                if(countmove >10){
                line=new SpeedLine(x,y);
                if(line.equals(null)){return;}
                line.draw();
                countmove=0;
            }
            //when mouse move too fast it will throws null pointer.I can't handle so I make a delay.
            }
            
        
        }
      
        if (action.equals("released")){
            if (this.ball==null) {
                UI.printMessage("Press Core/Completion button first to create a ball");
                return;  // the ball hasn't been constructed yet.
            }            
            if (this.ball.getX()==LAUNCHER_X && this.ball.getHeight()==LAUNCHER_HEIGHT-20){
                double speedX = (x-LAUNCHER_X)/10;
                double speedUp = (GROUND - 20 - y)/10;
                double speed = Math.hypot(speedUp, speedX);
                //scale down if over the maximum allowed speed
                if (speed> MAX_SPEED){
                    speedUp = speedUp * MAX_SPEED/speed;
                    speedX = speedX * MAX_SPEED/speed;
                }
                isLaunch=true;
                this.ball.setSpeed(speedX, -speedUp);
                this.ball.step();
            }
        }
    }
    
    public void move(String action,double x ,double y){
        if(isLaunch){return;}
        
        UI.println(action);
    
    }
    public void runGameChallenge(){
        gameIsStart=true;
        count=0;
        
        
        
        UI.println("Challenge Edition");
        UI.printMessage("Click Mouse to launch the ball");
        UI.println("If you find that main ball occur bug,plase press \"Reset\" !");
        this.ball = new C_Ball(LAUNCHER_X, LAUNCHER_HEIGHT-20);
        int targetNumbers=UI.askInt("target(Max 6): ");
        if(targetNumbers >6){targetNumbers=targetNumbers/targetNumbers * 6;}
       /* set target */ /* set target */ /* set target */
        this.setTarget(targetNumbers);
       
        /* set Obstacles */ /* set Obstacles */ /* set Obstacles */
  
        
        this.drawGameChallenge(this.ball);
        this.setBase(); 
        boolean gameIsOver=false;

        // run until the target is gone (ie, off the right end)
        while (ball.getX()!=LAUNCHER_X || !gameIsOver){

            //if the ball is over the right end, make a new one.
            if (ball.getX()>=RIGHT_END || ball.getX()<LAUNCHER_X) {
                isLaunch=false;
                this.ball = new C_Ball(LAUNCHER_X, LAUNCHER_HEIGHT-20);
                count++;
            }

            //move the ball, if it isn't on the launcher
            if (ball.getX() > LAUNCHER_X){
                this.ball.step();//firsttime
            }
        //main ball To targetBall    
            for(C_Ball target: targetList){
            // move target if it isn't on the shelf
             if(this.ball.getX()==LAUNCHER_X && this.ball.getHeight()==LAUNCHER_HEIGHT-20){break; }
            
            
            
            //if ball is hitting the target ball on the shelf, then make it start moving too
            
            if (this.ball.getDist(target)<=2* Ball.RADIUS){
                target.setSpeed(this.ball.getStepX(),this.ball.getStepY());
                target.isMove=true;
                target.step();
                this.ball.setSpeed(-this.ball.getStepX(),-this.ball.getStepY());
            }
             
        }
        
        //main ball To shelf
            for(Obstacles shelf: obstaclesList){
                if(this.ball.getX()==LAUNCHER_X && this.ball.getHeight()==LAUNCHER_HEIGHT-20){break; }
                
                
                if(shelf.getDist(this.ball) <= Ball.RADIUS){
                    shelf.isMove = true;
                    shelf.setSpeed(this.ball.getStepX(),this.ball.getStepY());
                    this.ball.setSpeed(-this.ball.getStepX(),-this.ball.getStepY());
                    shelf.step();
                }
            
            }
        //targetBall To targetBall
            for(int i=0;i<targetList.size();i++){
                for(int y=i+1;y<targetList.size();y++){
                    if (targetList.get(i).getDist(targetList.get(y))<=2* Ball.RADIUS){
                        if(targetList.get(i).isMove){
                            targetList.get(y).setSpeed(targetList.get(i).getStepX(),targetList.get(i).getStepY());
                            targetList.get(y).isMove=true;
                            targetList.get(i).setSpeed(-targetList.get(i).getStepX(),-targetList.get(i).getStepY());
                        }else{
                            targetList.get(i).setSpeed(targetList.get(y).getStepX(),targetList.get(y).getStepY());
                            targetList.get(i).isMove=true;
                            targetList.get(y).setSpeed(-targetList.get(y).getStepX(),-targetList.get(y).getStepY());
                        }
                   }
                }
            }
            
        //targetBall To shelf
            for(C_Ball target:targetList){
                for(Obstacles shelf:obstaclesList){
                    if(shelf.isVisible){
                    if(shelf.getDist(target)<=Ball.RADIUS){
                        if(shelf.isMove){
                            target.isMove=true;
                            target.setSpeed(shelf.getStepX(),shelf.getStepY());
                            shelf.setSpeed(-shelf.getStepX(),-shelf.getStepY());
                        }else{
                            shelf.isMove=true;
                            shelf.setSpeed(target.getStepX(),target.getStepY());
                            target.setSpeed(-target.getStepX(),-target.getStepY());
                        }
                    }
                }
            }
        }
        
        //shelf To shelf
            for(int i=0;i<obstaclesList.size();i++){
                if(obstaclesList.get(i).isMove){
                obstaclesList.get(i).step();
            }
                for(int y=i+1;y<obstaclesList.size();y++){
                if(obstaclesList.get(i).isVisible){
                    if(obstaclesList.get(i).getDist(obstaclesList.get(y))<=Math.hypot(16,4)){
                    if(obstaclesList.get(i).isMove){
                        obstaclesList.get(y).isMove=true;
                        obstaclesList.get(y).setSpeed(obstaclesList.get(i).getStepX(),obstaclesList.get(i).getStepY());
                        obstaclesList.get(i).setSpeed(-obstaclesList.get(i).getStepX(),-obstaclesList.get(i).getStepY());
                    }else{
                         obstaclesList.get(i).isMove=true;
                        obstaclesList.get(i).setSpeed(obstaclesList.get(y).getStepX(),obstaclesList.get(y).getStepY());
                        obstaclesList.get(y).setSpeed(-obstaclesList.get(y).getStepX(),-obstaclesList.get(y).getStepY());
                                                            }
                    }
                }
                }
            }
            //is finshed ?
            
            
            
            
            int isThatAll=0;
            for(C_Ball target: targetList){
                if(target.isMove){
                target.step();
            }
               
             if(target.getX()>RIGHT_END ||target.getX()<LAUNCHER_X){
              isThatAll++;
             }
             if(isThatAll == targetNumbers){
                gameIsOver=true;
                isThatAll=0;
                }
            }
            isThatAll=0;
            
            
            this.drawGameChallenge(this.ball);
            

            UI.sleep(40); // pause of 40 milliseconds

        }
        UI.setFontSize(120);
        UI.drawString(count+" tries", 200, 200);
        targetList.clear();
        targetH.clear();
        obstaclesList.clear();
        gameIsStart=false;
    }

    public void drawGameChallenge(C_Ball ball){        
        UI.clearGraphics();
        ball.draw();
         for(C_Ball cBall:targetList){
         cBall.draw();
        }
        
         for(Obstacles shelf:obstaclesList){
          shelf.draw();  
            }
         
        UI.setColor(Color.black);
        UI.setLineWidth(2);
        // draw ground, wall, launcher, and shelf
        UI.eraseRect(RIGHT_END, 0, RIGHT_END+100, GROUND);
        UI.drawLine(LAUNCHER_X, GROUND, RIGHT_END, GROUND);
        UI.drawLine(RIGHT_END, GROUND, RIGHT_END, 0);
        UI.drawLine(LAUNCHER_X, GROUND, LAUNCHER_X, LAUNCHER_HEIGHT);
        UI.drawLine(LAUNCHER_X-Ball.RADIUS, LAUNCHER_HEIGHT, LAUNCHER_X+Ball.RADIUS, LAUNCHER_HEIGHT);
    }

    public void setBase(){
        int i=0;
        for(double height:targetH){
           
         switch(i){
          case 0:obstaclesList.add(new Obstacles(SHELF_X12,height)); i++; continue;
          case 1:obstaclesList.add(new Obstacles(SHELF_X12,height)); i++; continue;
          case 2:obstaclesList.add(new Obstacles(SHELF_X34,height)); i++; continue;
          case 3:obstaclesList.add(new Obstacles(SHELF_X34,height)); i++; continue;
          case 4:obstaclesList.add(new Obstacles(SHELF_X56,height)); i++; continue;
          case 5:obstaclesList.add(new Obstacles(SHELF_X56,height)); return;
        }
        }
    }
    
    public void setTarget(int num){
    
        
          int Count=0;
             while(targetH.size()<num){
              this.addHeight(targetH);
          }
         for(double H:targetH){
             
             if(Count<2){
                 targetList.add(new C_Ball(SHELF_X12,H));
                 Count++;
                 continue;
                }
                
             if(Count<4 && Count>=2){
                 targetList.add(new C_Ball(SHELF_X34,H));
                 Count++;
                 continue;             
                }   
             
             if(Count<6 && Count>=4){
                targetList.add(new C_Ball(SHELF_X56,H));
                 Count++;
                 continue;
                }   
                
            }

    }
    
    public void addHeight(HashSet<Double> Hset){
        double num=Math.random()*400+50;
        boolean isTooClose=false;
            if(num>440){return;}
            if(num<150){return;}
        for(double Anum:Hset){
            if(Math.abs(Anum-num)<=Ball.RADIUS*2+10){
            isTooClose=true;
            }
        }
        
        if(isTooClose){return;}
        else{Hset.add(num);}
    }
    
    public void reset(){
        isLaunch=false;
        this.ball = new C_Ball(LAUNCHER_X, LAUNCHER_HEIGHT-20);
        count++;
        this.drawGameChallenge(this.ball);
        
       
    }
    
     public void setupGUI(){
        UI.setMouseMotionListener(this::launch); 
        UI.addButton("Challenge", this::runGameChallenge);
        
        UI.addButton("Reset", this::reset);
        UI.addButton("ShowSpeedLine",()->this.speedLineVisible=true);
        UI.addButton("CancelSpeedLine",()->this.speedLineVisible=false);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(650,500);
        UI.setDivider(0);

    }
    
    public static void main(String[] args){
        new BallGameChallenge().setupGUI();
    }
}