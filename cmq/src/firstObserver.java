import javax.swing.*;

public class firstObserver extends Observer{
    public firstObserver(Subject subject,JTextArea area){
        this.subject = subject;
        this.area=area;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        area.setText( "firstObserver: "
                + subject.getState());
    }

}
