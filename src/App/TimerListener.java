package App;

import App.Gui.Fields.Field;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {

    Field field;

    public TimerListener(JPanel jp) {
        this.field = (Field) jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        field.step1();
        field.step2();
    }
}
