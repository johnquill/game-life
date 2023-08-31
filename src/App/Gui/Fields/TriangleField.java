package App.Gui.Fields;

import App.Gui.Cells.Cell;
import App.Gui.Cells.Triangle;

import static java.lang.Math.*;

public class TriangleField extends Field {

    @Override
    public void initCells() {
        double height = (HEIGHT-100)/y;
        double width = WIDTH*0.9/(x+y+2)*2;
        int size = (int) min(sqrt(pow(height, 2)*3/4), width);
        int startX = (int) (20+WIDTH*0.9/2-size/2*(x+y+2)/2);
        int startY = (int) (20+(HEIGHT-100)/2-sqrt(pow(size, 2)*3/4)*y/2);
        initTriangleCells(x, y, size, startX, startY);
        if (isInfinity) {
            addNearCellsInfinity();
        } else {
            addNearCellsClosed();
        }
    }

    private void initTriangleCells(int x, int y, int size, int startX, int startY) {
        cells = new Cell[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                cells[i][j] = new Triangle(i, j, size, startX, startY);
                add(cells[i][j]);
            }
        }
    }

    private void addNearCellsInfinity() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                for (int s = j-2; s <= j+2; s++) {
                    if (s != j) {
                        cells[i][j].addNearCell(cells[i][(s + x) % x]);
                    }
                }
                if (j%2 != 0) {
                    for (int s = j; s <= j+2; s++) {
                        cells[i][j].addNearCell(cells[(i-1 + y) % y][s % x]);
                    }
                    for (int s = j-3; s <= j+1; s++) {
                        cells[i][j].addNearCell(cells[(i+1) % y][(s + x) % x]);
                    }
                } else {
                    for (int s = j-1; s <= j+3; s++) {
                        cells[i][j].addNearCell(cells[(i-1 + y) % y][(s + x) % x]);
                    }
                    for (int s = j-2; s <= j; s++) {
                        cells[i][j].addNearCell(cells[(i+1) % y][(s + x) % x]);
                    }
                }
            }
        }
    }

    private void addNearCellsClosed() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                for (int s = j-2; s <= j+2; s++) {
                    if (s != j && s >= 0 && s < x) {
                        cells[i][j].addNearCell(cells[i][s]);
                    }
                }
                if (j%2 != 0) {
                    if (i-1 >= 0) {
                        for (int s = j; s <= j + 2; s++) {
                            if (s < x)
                                cells[i][j].addNearCell(cells[i - 1][s]);
                        }
                    }
                    if (i+1 < y) {
                        for (int s = j - 3; s <= j + 1; s++) {
                            if (s >= 0 && s < x)
                                cells[i][j].addNearCell(cells[i + 1][s]);
                        }
                    }
                } else {
                    if (i-1 >= 0) {
                        for (int s = j - 1; s <= j + 3; s++) {
                            if (s >= 0 && s < x)
                                cells[i][j].addNearCell(cells[i-1][s]);
                        }
                    }
                    if (i+1 < y) {
                        for (int s = j - 2; s <= j; s++) {
                            if (s >= 0)
                                cells[i][j].addNearCell(cells[i + 1][s]);
                        }
                    }
                }
            }
        }
    }
}
