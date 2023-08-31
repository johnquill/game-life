package App.Gui.Cells;

import App.Condition;
import App.Config;

import java.awt.*;

import static java.lang.Math.sqrt;

public class Hexagon extends Cell {

    public Hexagon(int i, int j, int s, int startX, int startY) {
        super();

        this.i = i;
        this.j = j;
        this.s = s;

        int[] x = new int[] {s/2, s, s, s/2, 0, 0};
        int[] y = new int[] {0, (int) (s/(2*sqrt(3))), (int) (1.5*s/sqrt(3)), (int) (2*s/sqrt(3)),
                (int) (1.5*s/sqrt(3)), (int) (s/(2*sqrt(3)))};
        polygon = new Polygon(x, y, 6);

        setSize(s, (int) (2*s/sqrt(3)));
        setLocation(startX+(s/2*(i%2) + s*j), startY+(int) (1.5*s/sqrt(3)*i));
        Dimension size = getPreferredSize();
        size.width = s;
        size.height = (int) (2*s/sqrt(3));
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
            if (count == 2) {
                nextCond = Condition.LIFE;
            } else nextCond = Condition.DEAD;
        } else {
            if (count == 3 || count == 4) {
                nextCond = Condition.LIFE;
            } else nextCond = Condition.DEAD;
        }
    }
}
