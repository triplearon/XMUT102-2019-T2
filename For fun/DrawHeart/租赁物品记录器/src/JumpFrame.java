import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class JumpFrame {
    private JFrame a;
    private  JTextField name;
    private JRadioButton man;
    private JRadioButton woman;
    private JRadioButton SSR;
    private JRadioButton money;
    private JTextField SDate;
    private JTextField howLong;
    private JLabel advice;
    private int y;
    private int m;
    private int d;
    private boolean isRight = true;



    public JumpFrame() {
        a = new JFrame("新用户");
    }

    public void setJumpGui() {
        a.setSize(400, 400);
        a.setLayout(null);
        a.setVisible(true);
        Font MBC = new Font(Font.DIALOG, Font.BOLD, 16);

        JLabel title = new JLabel("新建用户");
        title.setBounds(0, 0, 400, 50);
        a.getContentPane().add(title);


        ZLabel un = new ZLabel("名字: ", 0, 50, MBC);
        ZLabel ua = new ZLabel("性别: ", 0, 100, MBC);
        ZLabel ut = new ZLabel("租赁类别: ", 0, 150, MBC);
        ZLabel us = new ZLabel("起始日期: ", 0, 200, MBC);
        ZLabel ue = new ZLabel("租赁时长: ", 0, 250, MBC);
        a.getContentPane().add(un);
        a.getContentPane().add(ua);
        a.getContentPane().add(ut);
        a.getContentPane().add(us);
        a.getContentPane().add(ue);

        man = new JRadioButton("男");
        man.setBounds(50, 112, 40, 30);
        woman = new JRadioButton("女");
        woman.setBounds(100, 112, 40, 30);
        ButtonGroup sex = new ButtonGroup();
        sex.add(man);
        sex.add(woman);

        SSR = new JRadioButton("SSR");
        SSR.setBounds(70, 162, 60, 30);
        money = new JRadioButton("￥");
        money.setBounds(130, 162, 80, 30);
        ButtonGroup tp = new ButtonGroup();
        tp.add(SSR);
        tp.add(money);
        name = new JTextField(6);
        name.setBounds(50, 62, 120, 30);
        SDate = new JTextField(16);
        SDate.setBounds(75, 212, 180, 30);
        howLong = new JTextField(6);
        howLong.setBounds(75, 262, 60, 30);

        a.getContentPane().add(name);
        a.getContentPane().add(man);
        a.getContentPane().add(woman);
        a.getContentPane().add(SSR);
        a.getContentPane().add(money);
        a.getContentPane().add(SDate);
        a.getContentPane().add(howLong);

        JButton save = new JButton("保存");
        save.setBounds(300, 325, 80, 30);
        save.addActionListener(new save());
        a.getContentPane().add(save);
        JButton exit = new JButton("返回");
        exit.setBounds(300, 290, 80, 30);
        exit.addActionListener(new exit());
        a.getContentPane().add(exit);

        advice = new JLabel("提示：日期请用/隔开年月日");
        advice.setBounds(0, 300, 200, 80);
        a.getContentPane().add(advice);
    }


    public class exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            a.dispose();
        }
    }

    public class save implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            makeUser();
        }

        private void makeUser() {

            getDate();
            int day = Integer.parseInt(howLong.getText());
            User an = new User(y, m, d, day);
            an.setSex(getSex());
            an.setName(name.getText());
            an.setType(getType());
            if (isRight) {
                try {
                    JFileChooser JFC = new JFileChooser("D:\\Java code\\租赁记录器\\Date");
                    JFC.showSaveDialog(a);

                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(JFC.getSelectedFile()));
                    oos.writeObject(an);
                    advice.setText("保存成功,即将退出该页面");
                    a.dispose();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        private void getDate() {
            try {
                String[] CurrentDate = SDate.getText().split("/");
                y = Integer.parseInt(CurrentDate[0]);
                m = Integer.parseInt(CurrentDate[1]);
                d = Integer.parseInt(CurrentDate[2]);
            } catch (Exception e) {
                isRight = false;
                advice.setText("日期错误 请退出该界面重试");
            }
        }

        private String getSex() {

            if (man.isSelected()) {
                return "男";
            } else if (woman.isSelected()) {
                return "女";
            } else {
                isRight = false;
                advice.setText("性别错误 请退出该页面重试");
                return "";
            }
        }

        private String getType() {
            if (SSR.isSelected()) {
                return "SSR";
            } else if (money.isSelected()) {
                return "人民币";
            } else {
                advice.setText("种类错误 请退出后重试");
                return "";
            }

        }


    }

}
