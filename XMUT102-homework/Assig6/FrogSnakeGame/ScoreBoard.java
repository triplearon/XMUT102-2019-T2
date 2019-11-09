import ecs100.*;
/**
 * Write a description of class ScoreBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ScoreBoard
{
    public int ScoreA;
    public int ScoreB;
    
    public void drawScoreBoard(int a, int b){
        ScoreA=a;
        ScoreB=b;
        
        UI.setLineWidth(2);
        UI.drawRect(460,50,300,100);
        UI.drawLine(460,80,760,80);
        UI.drawLine(510,50,510,150);
        UI.drawLine(635,50,635,150);
        UI.drawImage("lightfrog.jpg",557.5,52,26,26);
        UI.drawImage("darkfrog.jpg",682.5,52,26,26);
        UI.setFontSize(50);
        UI.drawString(""+ScoreA,557.5,140);
        UI.drawString(""+ScoreB,682.5,140);
    }
}
//只是很简单的计分板 很简单
