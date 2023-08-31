package App.Gui.Cells;

import App.Condition;
import App.Config;

import java.awt.*;

import static java.lang.Math.sqrt;

public class Triangle extends Cell {

    public Triangle(int i, int j, int s, int startX, int startY) {
        super();

        this.i = i;
        this.j = j;
        this.s = s;
        int[] x;
        int[] y;
        if (j%2 != 0) {
            x = new int[] {s/2, s, 0};
            y = new int[] {0, (int) (sqrt(s*s*0.75)), (int) (sqrt(s*s*0.75))};
        } else {
            x = new int[] {0, s, s/2};
            y = new int[] {0, 0, (int) (sqrt(s*s*0.75))};
        }
        polygon = new Polygon(x, y, 3);

        setSize(s, (int) (s*s*0.75));
        setLocation(startX + s/2*j + s/2*i, (int) (startY + sqrt(s*s*0.75)*i));
        Dimension size = getPreferredSize();
        size.width = size.height = s;
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(curCond == Condition.LIFE? Color.WHITE : Color.BLACK);
        g.fillPolygon(polygon);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawPolygon(polygon);
    }

    Shape shape;
    @Override
    public boolean contains(int x, int y) {
        shape = polygon;
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
            if (count == 4 || count == 8) {
                nextCond = Condition.LIFE;
            } else nextCond = Condition.DEAD;
        } else {
            if (count >= 4 && count <= 6) {
                nextCond = Condition.LIFE;
            } else nextCond = Condition.DEAD;
        }
    }
}
