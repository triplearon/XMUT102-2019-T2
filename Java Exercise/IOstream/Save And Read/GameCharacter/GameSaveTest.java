import java.io.*;


public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter a=new GameCharacter(50,"Elf",new String[]{"bow","sword","dust"});
        GameCharacter b=new GameCharacter(200,"Troll",new String[]{"bare hands","big ax"});
        GameCharacter c=new GameCharacter(120,"Magician",new String[]{"spells","invisibility"});
        //实例化三个游戏角色

        try {
            ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("Game save.ser"));
            //建立输出流 存储在Game save.ser
            
            os.writeObject(a);
            os.writeObject(b);
            os.writeObject(c);
            //由os向文件以此写入a b c对象
            
            os.close();
            //关闭输出流
        }catch (Exception e){
            e.printStackTrace();
        }

        a=null;
        b=null;
        c=null;
    //重置引用变量 使得建立好的对象被回收 用该步测试对象数据是否被写入到文档并保存起来
        try{
            ObjectInputStream is=new ObjectInputStream(new FileInputStream("Game save.ser"));
            //打开输入流
            
            GameCharacter A=(GameCharacter) is.readObject();
            GameCharacter B=(GameCharacter) is.readObject();
            GameCharacter C=(GameCharacter) is.readObject();
            //读取对象，但读取的对象为Object类 强制转化为GameCharacter类以加载
            System.out.println(A.getType()+" "+A.getPower()+" "+A.getWeapons());
            System.out.println(B.getType()+" "+B.getPower()+" "+B.getWeapons());
            System.out.println(C.getType()+" "+C.getPower()+" "+C.getWeapons());
            //输出对象属性 观察是否被恢复
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
