package App.Gui.Panels;

import App.Config;
import App.Gui.Dialog;
import App.Gui.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsPanel extends JPanel {

    private JTextField widthField;
    private JTextField heightField;
    private JRadioButton infinityButton;
    private JComboBox comboBox;
    private Frame frame;

    public SettingsPanel(Frame frame) {
        this.frame = frame;
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.add(addBorderRadio());
        pane.add(addSizeFields());
        pane.add(addComboBox());
        pane.add(addApplyButton());
        add(pane);
    }

    private void apply() {
        int newW = getWeightX();
        int newH = getHeightY();
        if (newH*newW > 5000) {
            new Dialog(frame, Dialog.ERROR, Dialog.ERROR_MUCH_SETTING);
        } else {
            if (newH > 0 && newW > 0) {
                heightField.setBackground(Color.white);
                widthField.setBackground(Color.white);
                Config.setFieldConfigs(newW, newH, isInfinityBorders(), getType());
                frame.reconstructField();
                repaint();
            }
        }
    }

    private JComboBox addComboBox() {
        comboBox = new JComboBox(new String[]{"Квадрат", "Шестиугольник", "Треугольник"});
        comboBox.setAlignmentX(LEFT_ALIGNMENT);
        return comboBox;
    }

    private JPanel addSizeFields() {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.add(new JLabel("Размер поля:"));
        JPanel size = new JPanel();
        size.setLayout(new BoxLayout(size, BoxLayout.LINE_AXIS));
        widthField = new JTextField(3);
        widthField.setText("30");
        size.add(widthField);
        size.add(new JLabel(" x "));
        heightField = new JTextField(3);
        heightField.setText("30");
        size.add(heightField);
        pane.add(size);
        pane.setAlignmentX(LEFT_ALIGNMENT);
        return pane;
    }

    private JPanel addBorderRadio() {
        JPanel radio = new JPanel();
        radio.setLayout(new BoxLayout(radio, BoxLayout.PAGE_AXIS));
        radio.add(new JLabel("     Граница"));
        ButtonGroup bg = new ButtonGroup();
        infinityButton = new JRadioButton("Бесконенчая", true);
        bg.add(infinityButton);
        JRadioButton closedButton = new JRadioButton("Закрытая");
        bg.add(closedButton);
        radio.add(infinityButton);
        radio.add(closedButton);
        radio.setAlignmentX(LEFT_ALIGNMENT);
        return radio;
    }

    private JButton addApplyButton() {
        JButton apply = new JButton("Применить");
        apply.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (frame.getGameRunning()) {
                    new Dialog(frame, Dialog.ERROR, Dialog.ERROR_CHANGE_SETTING);
                } else {
                    apply();
                }
            }
        });
        return apply;
    }

    private int getType() {
        return comboBox.getSelectedIndex();
    }

    private boolean isInfinityBorders() {
        return infinityButton.isSelected();
    }

    public int getHeightY() {
        int newH = 0;
        try {
            newH = Integer.parseInt(heightField.getText());
            if (!(newH > 0)) {
                heightField.setBackground(Color.RED);
                new Dialog(frame, Dialog.ERROR, Dialog.ERROR_FILL_SETTING);
            }
        } catch (NumberFormatException e) {
            heightField.setBackground(Color.RED);
            new Dialog(frame, Dialog.ERROR, Dialog.ERROR_FILL_SETTING);
        }
        return newH;
    }

    public int getWeightX() {
        int newW = 0;
        try {
            newW = Integer.parseInt(widthField.getText());
            if (!(newW > 0)) {
                widthField.setBackground(Color.RED);
                new Dialog(frame, Dialog.ERROR, Dialog.ERROR_FILL_SETTING);
            }
        } catch (NumberFormatException e) {
            widthField.setBackground(Color.RED);
            new Dialog(frame, Dialog.ERROR, Dialog.ERROR_FILL_SETTING);
        }
        return newW;
    }

    public void updateSettings() {
        widthField.setText(String.valueOf(Config.fieldWidth));
        heightField.setText(Integer.toString(Config.fieldHeight));
        comboBox.setSelectedIndex(Config.cellsType);
        apply();
    }
}