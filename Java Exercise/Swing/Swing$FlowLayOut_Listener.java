import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowLayOut {
    public void go(){
        JFrame frame=new JFrame();

        buttonn a=new buttonn();
        a.setButton(new JButton("123"));
        JButton button=a.getButton();

        buttonn b=new buttonn();
        b.setButton(new JButton("哈批"));
        JButton B=b.getButton();

        button.addActionListener(new buttonn());



        JPanel panel=new JPanel();
        panel.setBackground(Color.darkGray);
        panel.add(button);


        frame.getContentPane().add(BorderLayout.EAST,panel);
        frame.getContentPane().add(BorderLayout.WEST,B);
        frame.setSize(200,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        FlowLayOut a=new FlowLayOut();
        a.go();
    }
    static class buttonn implements ActionListener{
        private JButton button;

        public JButton getButton() {
            return button;
        }

        public void setButton(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            button.setText("呕呕呕！");
        }
    }


    }

//有bug 抛了空指针 留在这里供以后理解深入后复习
