import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Jukebox1 {

    ArrayList<String> songList =new ArrayList<String>();

    public static void main(String[] args) {
        new Jukebox1().go();

    }
    public void go(){
        getSong();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
    }

    void getSong(){
        try{
            File file=new File("D:\\Java code\\集合与泛型\\点歌排序\\songList.txt");
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line= null;
            while((line=reader.readLine())!=null){
                addSong(line);
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }

    }

    void addSong(String line){
        String [] lines=line.split("/");
        songList.add(lines[0]);

    }
}
//利用Collection下sort方法为ArrayList排序
