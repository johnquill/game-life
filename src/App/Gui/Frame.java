package App.Gui;

import App.Config;
import App.Gui.Panels.MenuBar;
import App.Gui.Panels.MainPanel;
import App.Gui.Panels.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Frame extends JFrame {

    private MainPanel mainPanel;
    private SettingsPanel settingsPanel;

    public Frame() {
        setTitle("Игра <<Жизнь>>");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        Config.SCREEN_WIDTH = (int) size.getWidth() - insets.left - insets.right;
        Config.SCREEN_HEIGHT = (int) size.getHeight() - insets.top - insets.bottom;
        setJMenuBar(new MenuBar(this));
        add(addSplitPanel());
        setVisible(true);
    }

    public void reconstructField() {
        mainPanel.createField();
        revalidate();
        repaint();
    }

    private JSplitPane addSplitPanel() {
        JSplitPane split = new JSplitPane();
        split.setResizeWeight(0.95);
        split.setOneTouchExpandable(true);
        split.setDividerSize(10);
        split.setLeftComponent(addMainPanel());
        split.setRightComponent(addSettingsPanel());
        return split;
    }

    private Component addMainPanel() {
        mainPanel = new MainPanel();
        return mainPanel;
    }

    private Component addSettingsPanel() {
        settingsPanel = new SettingsPanel(this);
        return settingsPanel;
    }

    public boolean getGameRunning() {
        return mainPanel.isRunning();
    }

    public void updateSettingsPanel() {
        settingsPanel.updateSettings();
    }

    public void fillFromFile(int[][] arr) {
        mainPanel.fillFromFile(arr);
    }

    public void writeFieldToFile(FileOutputStream writeStream) {
        mainPanel.writeToFile(writeStream);
    }

    public void exportToFile(File file) {
        try (FileOutputStream w = new FileOutputStream(file)) {
            w.write(123);
            w.write(231);
            w.write(Config.cellsType);
            w.write(Config.fieldWidth);
            w.write(Config.fieldHeight);
            writeFieldToFile(w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
