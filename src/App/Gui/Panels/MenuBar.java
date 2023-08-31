package App.Gui.Panels;

import App.Config;
import App.Gui.Dialog;
import App.Gui.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MenuBar extends JMenuBar {

    private Frame frame;
    JFileChooser fileChooser = new JFileChooser();

    public MenuBar(Frame frame) {
        this.frame = frame;
        add(createFileMenu());
        add(createReferenceMenu());
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть",
                new ImageIcon("images/open.png"));
        JMenuItem save = new JMenuItem("Сохранить",
                new ImageIcon("images/save.png"));
        file.add(open);
        file.addSeparator();
        file.add(save);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame.getGameRunning()) {
                    new Dialog(frame, Dialog.ERROR, Dialog.ERROR_OPEN_FILE);
                } else {
                    int result = fileChooser.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        int type = 0, x = 0, y = 0;
                        int[][] arr = new int[0][0];
                        FileInputStream r;
                        int code1;
                        int code2;
                        try {
                            r = new FileInputStream(file);
                            code1 = r.read();
                            code2 = r.read();
                            if (code1 == 123 && code2 == 231) {
                                type = r.read();
                                x = r.read();
                                y = r.read();
                                arr = new int[y][x];
                                for (int i = 0; i < y; i++) {
                                    for (int j = 0; j < x; j++) {
                                        arr[i][j] = r.read();
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            new Dialog(frame, Dialog.ERROR, Dialog.NOT_EXIST_FILE);
                            throw new RuntimeException(ex);
                        }
                        try {
                            r.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        if (code1 == 123 && code2 == 231) {
                            Config.cellsType = type;
                            Config.fieldHeight = y;
                            Config.fieldWidth = x;
                            frame.updateSettingsPanel();
                            frame.fillFromFile(arr);
                            frame.fillFromFile(arr);
                        } else new Dialog(frame, Dialog.ERROR, Dialog.ERROR_OTHER_FILE);
                    }
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    frame.exportToFile(file);
                }
            }
        });
        return file;
    }

    private Component createReferenceMenu() {
        JMenu reference = new JMenu("Справка");
        JMenuItem author = new JMenuItem("Автор",
                new ImageIcon("images/author.png"));
        reference.add(author);

        author.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dialog(frame, Dialog.AUTHOR, Dialog.AUTHOR_TEXT);
            }
        });
        return reference;
    }

}
