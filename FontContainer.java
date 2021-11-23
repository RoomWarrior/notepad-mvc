import java.awt.*;

interface FontContainer {

    String getSelectedStyle();

    float getSelectedSize();

    String getSelectedFamily();

    Font getSelectedFont();

    void setSelectedFont(Font font);

    void setPreviewFont(Font font);

}
