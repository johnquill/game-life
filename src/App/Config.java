package App;

public class Config {

    public static final int SQUARE = 0;
    public static final int HEXAGON = 1;
    public static final int TRIANGLE = 2;

    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static int sleep;

    public static int fieldWidth;
    public static int fieldHeight;
    public static boolean infinityBorder;
    public static int cellsType;

    static {
        sleep = 400;
        fieldWidth = fieldHeight = 30;
        infinityBorder = true;
        cellsType = SQUARE;
    }

    public static void setFieldConfigs(int wValue, int hValue, boolean infinityBorders, int type) {
        fieldWidth = wValue;
        fieldHeight = hValue;
        infinityBorder = infinityBorders;
        cellsType = type;
    }
}
