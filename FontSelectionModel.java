import java.awt.*;

interface FontSelectionModel {

    Font getSelectedFont();

    String getSelectedFontName();

    String getSelectedFontFamily();

    int getSelectedFontSize();

    void setSelectedFont(Font font);

}
