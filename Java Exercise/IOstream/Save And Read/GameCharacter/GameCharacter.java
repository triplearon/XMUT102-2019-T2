import java.io.Serializable;
//用来序列化测试的类
public class GameCharacter implements Serializable {    //可被序列化的类一定要接入Serializable接口
    int power;                 
    String type;
    String [] weapons;

    //游戏主角的属性
    
    public GameCharacter(int p,String y,String[] w){
        this.power=p;
        this.type=y;
        this.weapons=w;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public String getWeapons() {
        String weaponList="";
        for(int i=0;i<weapons.length; i++){

            weaponList+=this.weapons[i]+" ";
        }
        return  weaponList;
    }
}
