import java.io.*;

public class DungeonGame implements Serializable {
    public int x=3;
    transient long y=4;
    private short z=5;

    public int getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public short getZ() {
        return z;
    }

}

class  DungeonTest{
    public static void main(String[] args) {
        DungeonGame d=new DungeonGame();
        System.out.println(d.getX()+d.getZ()+d.getY());
        try {
            FileOutputStream fos = new FileOutputStream("dg.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(d);
            oos.close();

            FileInputStream fis=new FileInputStream("dg.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            d=(DungeonGame)ois.readObject();
            System.out.println(d.getX()+d.getY()+d.getZ());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
