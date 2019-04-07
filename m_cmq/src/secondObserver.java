import javax.swing.*;

public class secondObserver extends Observer{
    public secondObserver(Subject subject,JTextArea area){
        this.subject = subject;
        this.area=area;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        area.setText( "secondObserver: "+ subject.getState());
    }

}
