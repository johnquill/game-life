package App.Gui.Fields;

import App.Condition;
import App.Config;
import App.Gui.Cells.Cell;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

import static App.Config.SCREEN_HEIGHT;
import static App.Config.SCREEN_WIDTH;

public abstract class Field extends JPanel {

    protected static final int WIDTH = SCREEN_WIDTH;
    protected static final int HEIGHT = SCREEN_HEIGHT;
    Cell[][] cells;
    int x, y;
    boolean isInfinity;

    public Field() {
        super();
        setVisible(true);
        setLayout(null);
        x = Config.fieldWidth;
        y = Config.fieldHeight;
        isInfinity = Config.infinityBorder;
    }

    public abstract void initCells();

    public void clearCells() {
        for (Cell[] cellArr : cells) {
            for (Cell cell : cellArr) {
                cell.curCond = Condition.DEAD;
                cell.nextCond = Condition.DEAD;
            }
        }
        repaint();
    }

    public void step1() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                cells[i][j].step1();
            }
        }
    }
    public void step2() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                cells[i][j].step2();
            }
        }
    }

    public void writeToFile(FileOutputStream w) throws IOException {
        for (Cell[] cellArr : cells) {
            for (Cell cell : cellArr) {
                w.write(cell.curCond == Condition.LIFE ? 1 : 0);
            }
        }
    }

    public void loadTemplate(int[][] arr) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (arr[i][j] == 1) {
                    cells[i][j].curCond = Condition.LIFE;
                }
            }
        }
        repaint();
    }
}
