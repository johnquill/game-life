package App.Gui.Fields;

import App.Gui.Cells.Cell;
import App.Gui.Cells.Square;

import static java.lang.Math.min;

public class SquareField extends Field {

    @Override
    public void initCells() {
        int size = (int) min((HEIGHT-150)/(y+1), (WIDTH*0.85)/(x+1));
        int startX = (int) (WIDTH*0.85/2-size*x/2);
        int startY = (int) ((HEIGHT-150)/2-size*y/2);
        initSquareCells(x, y, size, startX, startY);
        if (isInfinity) {
            addNearCellsInfinity();
        } else {
            addNearCellsClosed();
        }
    }

    private void initSquareCells(int x, int y, int size, int startX, int startY) {
        cells = new Cell[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                cells[i][j] = new Square(i, j, size, startX, startY);
                add(cells[i][j]);
            }
        }
    }

    private void addNearCellsInfinity() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                for (int si = -1; si <= 1; si++) {
                    for (int sj = -1; sj <= 1; sj++) {
                        if (!(si == 0 && sj == 0)) {
                            cells[i][j].addNearCell(cells
                                    [(i + si + y) % y]
                                    [(j + sj + x) % x]
                            );
                        }
                    }
                }
            }
        }
    }

    private void addNearCellsClosed() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                for (int si = -1; si <= 1; si++) {
                    for (int sj = -1; sj <= 1; sj++) {
                        if (!(si == 0 && sj == 0)) {
                            if (!(i+si < 0 || i + si >= y || j+sj < 0 || j+sj >= x)) {
                                cells[i][j].addNearCell(cells
                                        [(i + si + y) % y]
                                        [(j + sj + x) % x]
                                );
                            }
                        }
                    }
                }
            }
        }
    }

}
