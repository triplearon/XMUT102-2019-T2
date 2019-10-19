/*Assig4的challenge作业字符
  要求：1，给定一个文件的名字，然后给定一个替换规则和替换词
       2，执行后输出一个新文件，文件名字为原文件名字后面加一个~
       3，将符合替换规则的字符串改为替换词
       4，用？代替正则表达式中的\\w{0}
       5，用*代替正则表达式中的\\w*
 特别注解：?在题目要求中匹配单个字符 例如b?a匹配所有baa bba bca bda bfa .... bza
         *在题目要求中匹配任意个字符 例如b*a匹配basdasdasdasdwa bewrinefinsa 但是不匹配badojawfojawz bawodjsgogf
         b*a可以看做匹配任意长度以b开头a结尾字符串 b？a＊t则匹配任意b开头 第二个字符随意 第三个字符必须是a 然后随意 结尾是b的字符串
         所以推荐用正则表达式，因为如果规则是b*a?c*d*a？z*d这种 正则表达式也可以很简单进行匹配
*/

//源码部分
import ecs100.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;
/**
 * FileEditor
 *
 * @author Deng Xinyu
 * @ID     XMUT_1812409208
 * @number 1812409208
 * @version BJ(with ecs100.jar)
 */
public class FileEditor
{   
    private String fileName;   //文件名
    private String Pattern;    //规则
    private String Replacement;//替换词
    
    static{
        UI.println("A ? matches any single character");
        UI.println("A * matches any number of adjacent characters on a line");
        UI.println("When you give a pattern,you don't need to follow full regular expression.");
        UI.println("For example,\"ba?na*na\" will match \"batnana\" \"banana\"");
        UI.println("There is no need to give a rull such as\"ba\\\\w?na\\\\w*na\"");
        UI.println("The output file will have the same path you give to me");
        UI.println("The FileEditor can run in Windows CMD,I wrote a CMD edtion named FileEditor_CMD.java");
        UI.println("====================================    ================================================");
        UI.println("");
        //READ ME
    }
    public FileEditor(){
    }//空构造函数 预留 其实没啥意义
    
    public FileEditor(String fileName,String Pattern,String Replacement){
     this.fileName=fileName;
     String Pattern_forebody=Pattern.replace("?","\\w{1}");
     this.Pattern=Pattern_forebody.replace("*","\\w*");
     this.Replacement=Replacement;
     //Constructor and transform the Pattern to full regular exoression
    }//构造函数，并且在构造对象的同时将题目预设的规则为程序可读的正则表达式规则 该构造方法中已经完成了要求1和要求4,5
    
    //输出替换后的文件方法
    public void outputFile()throws IOException{
     File file=new File(this.fileName+"~.txt");//实例化一个新对象 同时将其名字改成给定名字加~ 完成要求2
     //output file's name
     FileWriter writer=new FileWriter(file);//实例化一个writer 该writer扎根在file对象上
     file.createNewFile();//创造出该文件在目录。 完成要求2
     //creat output file and writer
     Scanner sc=new Scanner(new File(this.fileName+".txt"));//实例化一个sc扫描器，开始扫描给定的文件
        while(sc.hasNext()){
            writer.write(sc.next().replaceAll(this.Pattern,this.Replacement)+" ");//sc扫描器开始扫描 同时将结果输出给writer 让writer写入到File里
     }
     writer.flush();//强制输出writer内存的内容
     writer.close();//结束writer    
    }
    
    //主函数部分
    public static void main(String[] args){
    String fileName=UI.askString("FileName(without .txt): ");//请求文件名
    String Pattern=UI.askString("Pattern: ");//请求规则
    String Replacement=UI.askString("Replacement: ");//请求替换词
    
    FileEditor FE=new FileEditor(fileName,Pattern,Replacement);//实例化FE
    //set FE object
       try{
           FE.outputFile();//做输出文件方法
           UI.println("Done! Please cheak the path.");//提示语
    }catch(IOException e){UI.println("Fail: "+e);}//如果失败 则打印出错误类型
    
    }
  //完
    
    
}
