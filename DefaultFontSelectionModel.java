import java.awt.*;

class DefaultFontSelectionModel implements FontSelectionModel {

    private Font selectedFont;

    DefaultFontSelectionModel(Font font) {
        selectedFont = font;

    }

    @Override
    public Font getSelectedFont() {
        return selectedFont;
    }

    @Override
    public String getSelectedFontName() {
        return selectedFont.getName();
    }

    @Override
    public String getSelectedFontFamily() {
        return selectedFont.getFamily();
    }

    @Override
    public int getSelectedFontSize() {
        return selectedFont.getSize();
    }

    @Override
    public void setSelectedFont(Font font) {

        if (font != null && !selectedFont.equals(font)) {
            selectedFont = font;
        }
    }

}
