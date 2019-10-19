import java.io.Serializable;

public class GameCharacter implements Serializable {
    int power;
    String type;
    String [] weapons;

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
