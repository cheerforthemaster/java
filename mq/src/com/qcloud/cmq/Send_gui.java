package com.qcloud.cmq;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Send_gui extends JPanel{

    public static void main(String[] args)
    {
        new Send_gui();
    }

    private  JFrame frame;
    private JButton buttonOK;
    private JTextArea text1;
    private Container container;
    private String secretId="AKID2ua4MRFxPerreERnlW5ScZKsDakhMgdT";
    private String secretKey="oQqEbtyoY6OPJ5lLlvrXx6D8xa3KYeia";
    private String endpoint = "https://cmq-queue-sh.api.qcloud.com";

    public Send_gui(){
        this.buttonOK = new JButton("发送");
        this.text1 = new JTextArea();

        Dimension preferredSize = new Dimension(400,100);
        buttonOK.setPreferredSize(preferredSize);
        frame=new JFrame();

        container=frame.getContentPane();
        frame.setLayout(new GridLayout(2, 1));

        text1.setBorder (BorderFactory.createLineBorder(Color.gray,5));

        frame.add(buttonOK);
        frame.add(text1);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String test=text1.getText();
                Account account = new Account(endpoint,secretId, secretKey);
                Queue queue = account.getQueue("xlwWork");
                try {
                    String msgId = queue.sendMessage(test);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        container.add(buttonOK);
        frame.setBounds(100, 100, 300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
