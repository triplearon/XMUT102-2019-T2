import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class QuizCardPlayer {
    private JTextArea display;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;
    private QuizCard currentCard;
    private int currentCardIndex;
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

        JScrollPane qScrollPane=new JScrollPane(display);
        qScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        qScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        nextButton=new JButton("Show Question");
        mainPanel.add(qScrollPane);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar=new JMenuBar();
        JMenu fileMenu =new JMenu("File");
        JMenuItem loadFileItem=new JMenuItem("Load card set");
        loadFileItem.addActionListener(new OpenMenuListener());

        fileMenu.add(loadFileItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(500,450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {


                if (isShowAnswer) {
                    display.setText(currentCard.getAnswser());
                    nextButton.setText("Next Card");
                    isShowAnswer = false;
                } else {
                    if (currentCardIndex < cardList.size()) {
                        showNextCard();
                    } else {
                        display.setText("That was last card");
                        nextButton.setEnabled(false);
                    }
                }
            }catch (NullPointerException e1){
                System.out.println("Please choose a card and try again");
            }
        }
    }

    public class OpenMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nextButton.setEnabled(true);
            display.setText("");
            cardList=null;
            currentCard=null;
            currentCardIndex=0;

            JFileChooser fileOpen=new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());

        }
    }

    private void loadFile(File file){
        cardList=new ArrayList<QuizCard>();
        try{
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line=null;
            while((line = reader.readLine())!=null){
                makeCard(line);
            }
            reader.close();
        }catch (Exception e){
            System.out.println("Can't open file");
            System.out.println("Or you made a wrong card");
            e.printStackTrace();
        }
    }

    private void makeCard(String lineToParse){
        String [] result=lineToParse.split("/");
        QuizCard card=new QuizCard(result[0],result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }

    private void showNextCard(){
        currentCard=cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show answer");
        isShowAnswer=true;
    }
}

class PlayerTest{
    public static void main(String[] args) {
        QuizCardPlayer a=new QuizCardPlayer();
        a.go();
    }
}
