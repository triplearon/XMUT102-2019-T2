//摘自《head first java》e15p450 注解由上传者添加



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
//常规导包操作

public class QuizCardBuilder {

   private JFrame frame;
   private JTextArea question;
   private JTextArea answers;
   private ArrayList<QuizCard> cardList;
//监听器主要在该区域工作 随申明为实例变量 并用一个AL数组存card数据

   public void go(){
        frame=new JFrame("Quiz Card Builder");
        JPanel mainPanel=new JPanel();
        Font bigFont=new Font("sanserif",Font.BOLD,24);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//基本设置 以及实例化一个字体

        question=new JTextArea(6,20);          //6个字 20行
        question.setLineWrap(true);            //自动换行启动
        question.setWrapStyleWord(true);       //在单词过长时 直接将该单词也换至下一行
        question.setFont(bigFont);             //设定字体
        JScrollPane qScrollPane=new JScrollPane(question);                                              //实例化滚轮
        qScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);          //总是可以垂直
        qScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);       //不可以水平
//question区域设置 以下细节略

        answers=new JTextArea(6,20);
        answers.setLineWrap(true);
        answers.setWrapStyleWord(true);
        answers.setFont(bigFont);
        JScrollPane aScrollPane=new JScrollPane(answers);
        aScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//answers区域设置

        JButton nextButton =new JButton("Next Card"); 
        cardList=new ArrayList<QuizCard>();
        JLabel qLabel=new JLabel("Question:");
        JLabel aLabel=new JLabel("Answer:");
//一些基本设置 关于按钮 提示语和实例化一个al数组

        mainPanel.add(qLabel);
        mainPanel.add(qScrollPane);
        mainPanel.add(aLabel);
        mainPanel.add(aScrollPane);
        mainPanel.add(nextButton);
//从上往下依次在JPanel里增加q提示语 q文字区域 a提示语 a文字区域
        nextButton.addActionListener(new NextCardListener());//为按钮注册监听者
        JMenuBar menuBar=new JMenuBar();                     //新增一个菜单条
        JMenu fileMenu=new JMenu("File");                    //新增标题为"File"的菜单
        JMenuItem newMenuItem=new JMenuItem("New");          //为标题为File的菜单增加子选项"New"和"Save";
        JMenuItem saveMenuItem=new JMenuItem("Save");         

        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
//为子选项注册监听者

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
//添加组件过程
        frame.setJMenuBar(menuBar);//给框架设置菜单栏
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);//主幕布占位
        frame.setSize(500,600);
        frame.setVisible(true);

   }

   public class NextCardListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e) {
           QuizCard card=new QuizCard(question.getText(),answers.getText());
           cardList.add(card);
           clearCard();
       }//按钮的监听者 接收到事件后 创造card对象并加入到list里 同时清除该页面
   }

    public class SaveMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card=new QuizCard(question.getText(),answers.getText());
            cardList.add(card);

            JFileChooser fileSave=new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }//Save菜单子选项的监听者 接收到事件后 将当前卡加入到list里 同时选择一个文档 调用保存文件方法

    public class NewMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            cardList.clear();
            clearCard();
        }
    }//new菜单子选项的监听者 接收到事件后 清理掉cardlist和card 代表新的一系列card
    
    private void clearCard(){
        question.setText("");
        answers.setText("");
        question.requestFocus();
    }//将QA框的内容清除 同时将焦点拉回Q框

    private void saveFile(File file){
       try{
           BufferedWriter writer=new BufferedWriter(new FileWriter(file));

           for(QuizCard card:cardList){
               writer.write(card.getQuestion()+"/");
               writer.write(card.getAnswser()+"\n");
           }
           writer.close();
       }catch (Exception e){
           System.out.println("couldn't write the cardlist out");
           e.printStackTrace();
       }
    }//传入JFileChooser的文件后 为其打开输出流 将cardlist里面的每张卡片内容写至文档保存
}

class QCBTestDriver{
    public static void main(String[] args) {
        QuizCardBuilder a=new QuizCardBuilder();
        a.go();
    }
}
