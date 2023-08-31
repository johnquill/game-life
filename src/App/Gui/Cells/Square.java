package App.Gui.Cells;

import App.Condition;

import java.awt.*;

public class Square extends Cell {

    public Square(int i, int j, int s, int startX, int startY) {
        super();

        this.j = j;
        this.i = i;
        this.s = s;

        setSize(s, s);
        setLocation(startX+(s*j), startY+(s*i));
        Dimension size = getPreferredSize();
        size.width = size.height = s;
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(curCond == Condition.LIFE? Color.WHITE : Color.BLACK);
        g.fillPolygon(new int[] {0, s, s, 0}, new int[] {0, 0, s, s}, 4);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawPolygon(new int[] {0, s, s, 0}, new int[] {0, 0, s, s}, 4);
    }

    Shape shape;
    @Override
    public boolean contains(int x, int y) {
        shape = new Polygon(new int[] {0, s, s, 0}, new int[] {0, 0, s, s}, 4);
        return shape.contains(x, y);
    }

    @Override
    public void countNextCond() {
        int count = 0;
        for (Cell cell : nearCells) {
            if (cell.curCond == Condition.LIFE) {
                count++;
            }
        }
        if (curCond == Condition.DEAD) {
            if (count == 3) {
                nextCond = Condition.LIFE;
            } else nextCond = Condition.DEAD;
        } else {
            if (count == 2 || count == 3) {
                nextCond = Condition.LIFE;
            } else nextCond = Condition.DEAD;
        }
    }
}
