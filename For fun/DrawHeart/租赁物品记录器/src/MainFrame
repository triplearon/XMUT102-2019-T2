import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class MainFrame {
    private JFrame mainFrame;
    private JTextField name;
    private JTextField sex;
    private JTextField type;
    private JTextField startDay;
    private JTextField endDay;

    public void creatMainGUI() {

        mainFrame = new JFrame("租赁信息系统");
        mainFrame.setSize(500, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);


        Font BBC = new Font(Font.DIALOG, Font.BOLD, 24);
        Font MBC = new Font(Font.DIALOG, Font.BOLD, 16);

        JLabel Title = new JLabel("用户租赁信息");
        Title.setBounds(0, 0, mainFrame.getWidth(), 50);
        Title.setHorizontalAlignment(JLabel.CENTER);
        Title.setFont(BBC);
        mainFrame.getContentPane().add(Title);

        ZLabel un = new ZLabel("名字: ", 0, 50, MBC);
        ZLabel ua = new ZLabel("性别: ", 0, 100, MBC);
        ZLabel ut = new ZLabel("租赁类别: ", 0, 150, MBC);
        ZLabel us = new ZLabel("起始日期: ", 0, 200, MBC);
        ZLabel ue = new ZLabel("终止日期: ", 0, 250, MBC);
        mainFrame.getContentPane().add(un);
        mainFrame.getContentPane().add(ua);
        mainFrame.getContentPane().add(ut);
        mainFrame.getContentPane().add(us);
        mainFrame.getContentPane().add(ue);

        name = new JTextField(6);
        name.setDisabledTextColor(Color.BLACK);
        name.setBounds(50, 62, 120, 30);
        sex = new JTextField(2);
        sex.setDisabledTextColor(Color.BLACK);
        sex.setBounds(50, 112, 50, 30);
        type = new JTextField(5);
        type.setBounds(80, 162, 80, 30);
        type.setDisabledTextColor(Color.BLACK);
        startDay = new JTextField(10);
        startDay.setBounds(80, 212, 120, 30);
        startDay.setDisabledTextColor(Color.BLACK);
        endDay = new JTextField(10);
        endDay.setBounds(80, 262, 120, 30);
        endDay.setDisabledTextColor(Color.BLACK);

        name.setEnabled(false);
        sex.setEnabled(false);
        type.setEnabled(false);
        startDay.setEnabled(false);
        endDay.setEnabled(false);

        mainFrame.getContentPane().add(name);
        mainFrame.getContentPane().add(sex);
        mainFrame.getContentPane().add(type);
        mainFrame.getContentPane().add(startDay);
        mainFrame.getContentPane().add(endDay);

        JButton saveButton = new JButton("新建");
        saveButton.addActionListener(new JumpAnew());
        JButton readButton = new JButton("读取");
        readButton.addActionListener(new Reader());
        saveButton.setBounds(400, 480, 80, 30);
        readButton.setBounds(400, 520, 80, 30);
        mainFrame.getContentPane().add(saveButton);
        mainFrame.getContentPane().add(readButton);

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame main = new MainFrame();
        main.creatMainGUI();
    }


    public static class JumpAnew implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JumpFrame jumpFrame = new JumpFrame();
            jumpFrame.setJumpGui();
        }
    }

    public class Reader implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser JFC = new JFileChooser("D:\\Java code\\租赁记录器\\Date");
            JFC.showOpenDialog(mainFrame);

            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(JFC.getSelectedFile()));
                User a = (User) ois.readObject();
                GetDateFromAUser(a);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        private void GetDateFromAUser(User an) {
            name.setText(an.getName());
            sex.setText(an.getSex());
            type.setText(an.getType());

            int y=an.getStartDay().get(an.getStartDay().YEAR);
            int m=an.getStartDay().get(an.getStartDay().MONTH);
            int d=an.getStartDay().get(an.getStartDay().DAY_OF_MONTH);
            startDay.setText(y+"/"+m+"/"+d);

            y=an.getEndDay().get(an.getEndDay().YEAR);
            m=an.getEndDay().get(an.getEndDay().MONTH);
            d=an.getEndDay().get(an.getEndDay().DAY_OF_MONTH);
            endDay.setText(y+"/"+m+"/"+d);
        }
    }


}

class ZLabel extends JLabel {
    public ZLabel(String title, int x, int y, Font ft) {
        super(title);
        this.setFont(ft);
        this.setBounds(x, y, 100, 50);
    }
