import javax.swing.*;

public abstract class Observer {
    protected Subject subject;
    protected JTextArea area;
    public abstract void update();
}