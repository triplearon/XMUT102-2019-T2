import java.io.*;


public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter a=new GameCharacter(50,"Elf",new String[]{"bow","sword","dust"});
        GameCharacter b=new GameCharacter(200,"Troll",new String[]{"bare hands","big ax"});
        GameCharacter c=new GameCharacter(120,"Magician",new String[]{"spells","invisibility"});

        try {
            ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("Game save.ser"));
            os.writeObject(a);
            os.writeObject(b);
            os.writeObject(c);

            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        a=null;
        b=null;
        c=null;

        try{
            ObjectInputStream is=new ObjectInputStream(new FileInputStream("Game save.ser"));
            GameCharacter A=(GameCharacter) is.readObject();
            GameCharacter B=(GameCharacter) is.readObject();
            GameCharacter C=(GameCharacter) is.readObject();

            System.out.println(A.getType()+" "+A.getPower()+" "+A.getWeapons());
            System.out.println(B.getType()+" "+B.getPower()+" "+B.getWeapons());
            System.out.println(C.getType()+" "+C.getPower()+" "+C.getWeapons());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
