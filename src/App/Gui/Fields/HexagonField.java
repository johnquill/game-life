package App.Gui.Fields;

import App.Gui.Cells.Cell;
import App.Gui.Cells.Hexagon;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class HexagonField extends Field {

    @Override
    public void initCells() {
        double width = WIDTH*0.9/(x+1);
        double widthFromHeight = (HEIGHT-150)/(1.5*y+0.5)*sqrt(3);
        int size = (int) (min(widthFromHeight, width));
        int startX = (int) (20+WIDTH*0.9/2-size*x/2);
        int startY = (int) (20+(HEIGHT-150)/2-(size/sqrt(3)*1.5*y/2));
        initHexagonCells(x, y, size, startX, startY);
        if (isInfinity) {
            addNearCellsInfinity();
        } else {
            addNearCellsClosed();
        }
    }

    private void initHexagonCells(int x, int y, int size, int startX, int startY) {
        cells = new Cell[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                cells[i][j] = new Hexagon(i, j, size, startX, startY);
                add(cells[i][j]);
            }
        }
    }

    private void addNearCellsInfinity() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                for (int sr = j-1; sr <= j+1; sr++) {
                    if (sr != j) cells[i][j].addNearCell(cells[i][(x+sr)%x]);
                }
                for (int nsr = i-1; nsr <= i+1; nsr++) {
                    if (nsr != i) {
                        if (i%2 != 0) {
                            cells[i][j].addNearCell(cells[(y+nsr)%y][j]);
                            cells[i][j].addNearCell(cells[(y+nsr)%y][(j+1)%x]);
                        } else {
                            cells[i][j].addNearCell(cells[(y+nsr)%y][(x+j-1)%x]);
                            cells[i][j].addNearCell(cells[(y+nsr)%y][j]);
                        }
                    }
                }
            }
        }
    }

    private void addNearCellsClosed() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int sr = j-1; sr <= j+1; sr++) {
                    if (sr != j)
                        if (sr >= 0 && sr < y) cells[i][j].addNearCell(cells[i][sr]);
                }
                for (int nsr = i-1; nsr <= i+1; nsr++) {
                    if (nsr >= 0 && nsr < x)
                        if (nsr != i) {
                            if (i%2 != 0) {
                                cells[i][j].addNearCell(cells[nsr][j]);
                                if (j+1 < y) cells[i][j].addNearCell(cells[nsr][j+1]);
                            } else {
                                if (j-1 > 0) cells[i][j].addNearCell(cells[nsr][j-1]);
                                cells[i][j].addNearCell(cells[nsr][j]);
                            }
                        }
                }
            }
        }
    }
}
