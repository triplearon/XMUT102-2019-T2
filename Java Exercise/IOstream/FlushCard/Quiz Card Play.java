import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class QuizCardPlayer {
    private JTextArea display;  //展示用JTextArea
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;  //存取卡片列表的AL数组
    private JFrame frame;     
    private QuizCard currentCard;     //当前的卡片
    private int currentCardIndex;     //当前卡片的索引  
    private JButton nextButton;
    private boolean isShowAnswer;

    public void go(){
        frame=new JFrame();
        JPanel mainPanel=new JPanel();
        Font big=new Font("sanserif",Font.BOLD,24);
  
        display=new JTextArea(10,20);
        display.setFont(big);
        display.setLineWrap(true);
        display.setEditable(false);
       //基础框架设置以及字体设置
        JScrollPane qScrollPane=new JScrollPane(display);
        qScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        qScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        nextButton=new JButton("Show Question");
        mainPanel.add(qScrollPane);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
       //TextArea组件与Button组件设置 Button注册监听
        JMenuBar menuBar=new JMenuBar();
        JMenu fileMenu =new JMenu("File");
        JMenuItem loadFileItem=new JMenuItem("Load card set");
        loadFileItem.addActionListener(new OpenMenuListener());
       //菜单设置 菜单选项注册监听
        fileMenu.add(loadFileItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(500,450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       //框架布局

    }

    public class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (isShowAnswer) {//判断当前是否要展示答案 如果是 则展示答案 同时把是否要展示答案设置为否 把按钮设置为Next Card来切换至下一张卡
                    display.setText(currentCard.getAnswser());
                    nextButton.setText("Next Card");
                    isShowAnswer = false;
                } else {//如果当前不需展示答案 则会展示接下来的一张卡
                    if (currentCardIndex < cardList.size()) { //先判断当前卡的索引位置是否小于cardlist数组的size 如果是 则展示卡
                        showNextCard();
                    } else {//如果否 即为卡已经耗尽
                        display.setText("That was last card");
                        nextButton.setEnabled(false);
                    }
                }
            }catch (NullPointerException e1){//避免用户在没有设置卡的情况下点击nextButton
                System.out.println("Please choose a card and try again");
            }
        }
    }
  //Next Card按钮的监听者设置
    public class OpenMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nextButton.setEnabled(true);
            display.setText("");
            cardList=null;
            currentCard=null;
            currentCardIndex=0;
    //初始化卡片的各项数据 以便于可以多此重复利用
            JFileChooser fileOpen=new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
    //JFC用来选择文件→制卡
        }
    }
    //菜单按钮的监听设置
    
    private void loadFile(File file){
        cardList=new ArrayList<QuizCard>();//新实例化一个卡数组
        try{
            BufferedReader reader=new BufferedReader(new FileReader(file)); //建立一个缓冲输入流 从FileRead中输入数据
            String line=null; //初始化一个Line 
            while((line = reader.readLine())!=null){
                makeCard(line);
            }//当reader的readline不为空时 将该line制为卡
            reader.close();
            //跳出循环后关闭
        }catch (Exception e){
            System.out.println("Can't open file");
            System.out.println("Or you made a wrong card");
            e.printStackTrace();
        }
    }
//加载文件的方法
    
    private void makeCard(String lineToParse){
        String [] result=lineToParse.split("/"); //在builder中 卡用/分割问题和答案 在该步中用split分开问题和答案分别加入到一个String数组
        QuizCard card=new QuizCard(result[0],result[1]); //第一个数据为问题 第二个索引位置为答案 制卡
        cardList.add(card);                              //将卡片加入AL数组 存储起来   
        System.out.println("made a card");               //系统反馈代表制卡
    }
    //制卡方法

    private void showNextCard(){
        currentCard=cardList.get(currentCardIndex);   //由于索引是0基 所以当前卡即为从AL数组中索引位置get的卡
        currentCardIndex++;                           //得到卡后将索引推进
        display.setText(currentCard.getQuestion());   //将展示文本框中的内容设置为卡的问题
        nextButton.setText("Show answer");            //将按钮的内容设置为Show answer
        isShowAnswer=true;                            //同时打开展示开关 下一次点击按钮时展示答案
    }
}
//展示卡的方法

class PlayerTest{
    public static void main(String[] args) {
        QuizCardPlayer a=new QuizCardPlayer();
        a.go();
    }
}
