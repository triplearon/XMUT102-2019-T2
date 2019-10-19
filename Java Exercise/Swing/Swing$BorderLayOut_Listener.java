import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test_2 {
    private JButton buttonN;
    private JButton buttonE;
    private JButton buttonW;
    private JButton buttonS;
    private JButton buttonM;


    public static void main(String[] args) {
        Test_2 b=new Test_2();
        b.go();
    }

    public void go(){
        JFrame frame=new JFrame();

         buttonN=new JButton("North");
         buttonE=new JButton("East");
         buttonW=new JButton("West");
         buttonS=new JButton("South");
         buttonM=new JButton("Middle");
    
        //frame默认布局BoradLayOut 有5个方向 以下为frame获取组件
        frame.getContentPane().add(BorderLayout.NORTH,buttonN);
        frame.getContentPane().add(BorderLayout.WEST,buttonW);
        frame.getContentPane().add(BorderLayout.EAST,buttonE);
        frame.getContentPane().add(BorderLayout.SOUTH,buttonS);
        frame.getContentPane().add(BorderLayout.CENTER,buttonM);

        //为各自的按钮实例化内部类对象并调用监听方法
        buttonN.addActionListener(new North());
        buttonE.addActionListener(new East());
        buttonM.addActionListener(new Central());
        buttonS.addActionListener(new South());
        buttonW.addActionListener(new West());

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    //内部类 监听者
    class North implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonN.setText("N!");
        }
    }//北 以下同
    class West implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonW.setText("W!");
        }
    }
    class South implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonS.setText("S!");
        }
    }
    class Central implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonM.setText("C!");
        }
    }
    class East implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonE.setText("E!");
        }
    }

    //本页面用来测试Swing BoradLayOut布局以及建立内部类的监听者，为按钮注册不同类下监听者的方法。

}
