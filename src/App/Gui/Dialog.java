package App.Gui;

import javax.swing.*;

public class Dialog extends JDialog {

    public static final String ERROR = "Ошибка";
    public static final String AUTHOR = "Автор";
    public static final String AUTHOR_TEXT = "<html><p align = \"center\">РГАТУ<br>Студент группы ИПБ-20 " +
            "Ходяков Федор<br>Рыбинск, 2022 год</p><html>";
    public static final String ERROR_CHANGE_SETTING = "<html><p align=\"center\">Пока игра запущена, " +
            "настройки поля изменять нельзя.</p></html>";
    public static final String ERROR_FILL_SETTING = "<html><p align=\"center\">Размер поля должен быть " +
            "целым числом больше нуля.</p></html>";
    public static final String ERROR_MUCH_SETTING = "<html><p align=\"center\">Размер поля слишком большой</p></html>";
    public static final String ERROR_OPEN_FILE = "<html><p align = \"center\">Пока игра запущена, нельзя загружать " +
            "расстановку из файла</p></html>";
    public static final String ERROR_OTHER_FILE = "<html><p align=\"center\">Попытка открыть сторонний файл</p></html>";
    public static final String NOT_EXIST_FILE = "<html><p align=\"center\">Попытка открыть несуществующий файл</p></html>";

    public Dialog(Frame frame, String type, String text) {
        super(frame, true);
        setTitle(type);
        setSize(200, 150);
        setLocation(frame.getWidth()/2-100, frame.getHeight()/2-75);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
