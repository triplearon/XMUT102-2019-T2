import javax.swing.*;
import java.awt.*;

public class FlowLayOut {
    public void go(){
        JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        panel.setBackground(Color.darkGray);

        JButton button=new JButton("123");
        JButton button1=new JButton("234");
        JButton button2=new JButton("345");

        panel.add(button);
        panel.add(button1);
        panel.add(button2);

        frame.getContentPane().add(BorderLayout.EAST,panel);
        frame.setSize(250,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        FlowLayOut a=new FlowLayOut();
        a.go();
    }



    }
    //由此看出 FlowLayOut布局向左延申 即使垂直空间足够
