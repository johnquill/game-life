package App.Gui.Panels;

import App.Config;
import App.Gui.Fields.Field;
import App.Gui.Fields.HexagonField;
import App.Gui.Fields.SquareField;
import App.Gui.Fields.TriangleField;
import App.TimerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;

import static App.Config.*;

public class MainPanel extends JPanel {

    private Timer timer;
    private boolean isRunning;
    private Field field;

    public MainPanel() {
        setLayout(new BorderLayout());
        isRunning = false;
        createField();
        add(addButtons(), BorderLayout.SOUTH);
    }

    private JPanel addButtons() {
        JPanel bp = new JPanel();
        bp.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton minusSpeedButton = new JButton("-speed");
        JButton plusSpeedButton = new JButton("+speed");
        pauseButton.setVisible(false);
        minusSpeedButton.setVisible(false);
        plusSpeedButton.setVisible(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (startButton.getText().equals("Start")) {
                    initTimer();
                    timer.start();
                    isRunning = true;
                    startButton.setText("Stop");
                    pauseButton.setVisible(true);
                    pauseButton.setText("Pause");
                    minusSpeedButton.setVisible(true);
                    plusSpeedButton.setVisible(true);
                } else {
                    timer.stop();
                    isRunning = false;
                    field.clearCells();
                    startButton.setText("Start");
                    pauseButton.setVisible(false);
                    minusSpeedButton.setVisible(false);
                    plusSpeedButton.setVisible(false);
                }
            }
        });
        pauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pauseButton.getText().equals("Play")) {
                    timer.start();
                    pauseButton.setText("Pause");
                } else {
                    timer.stop();
                    pauseButton.setText("Play");
                }
            }
        });
        minusSpeedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (sleep < 800) {
                    sleep += 100;
                    timer.setDelay(sleep);
                } else minusSpeedButton.setVisible(false);
                if (sleep > 100) {
                    plusSpeedButton.setVisible(true);
                }
            }
        });
        plusSpeedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (sleep > 100) {
                    sleep -= 100;
                    timer.setDelay(sleep);
                } else plusSpeedButton.setVisible(false);
                if (sleep < 800) {
                    minusSpeedButton.setVisible(true);
                }
            }
        });
        bp.add(startButton);
        bp.add(pauseButton);
        bp.add(minusSpeedButton);
        bp.add(plusSpeedButton);
        return bp;
    }

    void initTimer() {
        TimerListener timerListener = new TimerListener(field);
        timer = new Timer(sleep, timerListener);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void fillFromFile(int[][] arr) {
        field.loadTemplate(arr);
    }

    public void writeToFile(FileOutputStream writeStream) {
        try {
            field.writeToFile(writeStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createField() {
        if (field != null) remove(field);
        switch (Config.cellsType) {
            case Config.SQUARE:
                field = new SquareField();
                break;
            case Config.TRIANGLE:
                field = new TriangleField();
                break;
            case Config.HEXAGON:
                field = new HexagonField();
                break;
        }
        field.initCells();
        add(field);
    }
}