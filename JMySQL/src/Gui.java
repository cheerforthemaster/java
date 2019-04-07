import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class Gui {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JDBC");

        frame.setContentPane(new Gui().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(600,500);
        frame.setPreferredSize(new Dimension(800,600));

        JMenuBar mBar=new JMenuBar();
        frame.setJMenuBar(mBar);
        JMenu sMenu, jMenu, gMenu, cMenu, bMenu;
        sMenu = new JMenu("File");
        jMenu = new JMenu("View");
        gMenu = new JMenu("Command");
        cMenu = new JMenu("Recent");
        bMenu = new JMenu("Options");
        mBar.add(sMenu);
        mBar.add(jMenu);
        mBar.add(gMenu);
        mBar.add(cMenu);
        mBar.add(bMenu);

        JMenuItem sItem, cItem, tItem;
        sItem = new JMenuItem("打开文件");
        cItem = new JMenuItem("保存文件");
        tItem = new JMenuItem("退出");
        sMenu.add(sItem);
        sMenu.addSeparator();
        sMenu.add(cItem);
        sMenu.addSeparator();
        sMenu.add(tItem);

        JTree treeOne=new Gui().tree1;

        //treeOne.add();

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel;
    private JTextArea textArea1;
    private JTree tree1;
    private JTextArea textArea2;
    private JButton button1;
    private JButton button2;
    private JMenuBar mBar;

}
