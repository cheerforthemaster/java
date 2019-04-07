import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class NotePad {
    private JPanel panel1;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JDBC");

        frame.setContentPane(new JPanel());
        JTextArea text=new JTextArea();
        frame.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(text);
        frame.add(scrollPane, BorderLayout.CENTER);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,600));

        m_menu menu=new m_menu(frame);

        menu.getArea(text);
        frame.pack();
    }

}

class m_menu implements ActionListener{
    Container p;
    JMenuBar mb;
    JTextArea textArea;
    File file;
    final JMenu[] m = {new JMenu("文件"),
            new JMenu("编辑"),
            new JMenu("帮助")};
    final JMenuItem[][] mi = {{new JMenuItem("打开文件"), new JMenuItem("保存文件"), new JMenuItem("另存为")},
            {new JMenuItem("粘贴"), new JMenuItem("复制")},
            {new JMenuItem("啥都没有")}};
    public m_menu(JFrame mfram) {
        JFrame f = mfram;
        p = f.getContentPane();
        mb = new JMenuBar();
        for(int i=0; i<m.length; i++) {
            for(int j=0; j<mi[i].length; j++) {
                m[i].add(mi[i][j]);
                mi[i][j].addActionListener(this);
            }
            mb.add(m[i]);
        }
        f.setJMenuBar(mb);
        //f.pack();
        f.setVisible(true);

    }
    public  void getArea(JTextArea item){
        textArea=item;

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="打开文件"){
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showOpenDialog(null);
            if(returnVal == chooseFile.APPROVE_OPTION) {
                file = chooseFile.getSelectedFile();
                JOptionPane.showConfirmDialog(null, "你选择的文件名是："+chooseFile.getName(file),
                        "确认",JOptionPane.ERROR_MESSAGE);
                FileInputStream in= null;
                try {
                    in = new FileInputStream(file);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                Long len=file.length();
                byte []tempbyte=new byte[len.intValue()];
                try {
                    in.read(tempbyte);
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String tem=null;
                try {
                    tem=new String(tempbyte,"GBK");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }

                System.out.println(tem);
                textArea.setText("");
                textArea.append(tem);

            }
        }
        if(e.getActionCommand() == "另存为") {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showSaveDialog(null);
            if(returnVal == chooseFile.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                JOptionPane.showConfirmDialog(null,chooseFile.getName(f));
            }
            String tem=textArea.getText();
            FileOutputStream out=null;
            try {
                out=new FileOutputStream(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                out.write(tem.getBytes());
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getActionCommand()=="保存文件"){
            String filename="新建文件";
            int i=0;

            while (file==null||file.exists()) {
                file=new File(filename+String.valueOf(i)+".txt");
                i++;
            }
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            String tem=textArea.getText();
            FileOutputStream out=null;
            try {
                out=new FileOutputStream(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                out.write(tem.getBytes());
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
        if (e.getActionCommand()=="复制"){
            textArea.copy();

        }
        if (e.getActionCommand()=="粘贴"){
            textArea.paste();
        }

    }


}