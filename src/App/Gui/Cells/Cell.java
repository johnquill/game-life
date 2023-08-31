package App.Gui.Cells;

import App.Condition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

abstract public class Cell extends JButton {

    int i, j, s;
    Polygon polygon;
    public Condition curCond;
    public Condition nextCond;
    protected List<Cell> nearCells;

    public Cell() {
        curCond = Condition.DEAD;
        nextCond = Condition.DEAD;
        nearCells = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                change();
                repaint();
            }
        });
    }

    public void addNearCell(Cell cell) {
        nearCells.add(cell);
    }

    public abstract void countNextCond();

    public void step1() {
        countNextCond();
    }

    public void step2() {
        curCond = nextCond;
        nextCond = Condition.DEAD;
        repaint();
    }

    public void change() {
        if (curCond == Condition.LIFE) {
            curCond = Condition.DEAD;
        } else curCond = Condition.LIFE;
    }
}
