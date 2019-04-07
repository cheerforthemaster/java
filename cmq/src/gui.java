import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class gui extends JPanel{

    private  JFrame frame;
    private JButton buttonOK;
    private JTextArea text1,text2,text3;
    private Container container;


    public gui(){
        this.buttonOK = new JButton("确定");
        this.text1 = new JTextArea();
        this.text2 = new JTextArea();
        this.text3 = new JTextArea();

        frame=new JFrame();

        container=frame.getContentPane();
        frame.setLayout(new GridLayout(4, 1));

        text1.setBorder (BorderFactory.createLineBorder(Color.gray,5));
        text2.setBorder (BorderFactory.createLineBorder(Color.gray,5));
        text3.setBorder (BorderFactory.createLineBorder(Color.gray,5));

        frame.add(buttonOK);
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String test=text1.getText();

                Subject subject = new Subject();
                new secondObserver(subject,text2);
                new firstObserver(subject,text3);

                subject.setStat(test);
            }
        });

        container.add(buttonOK);
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
